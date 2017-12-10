package com.tevinjeffrey.rutgersct.data

import com.orhanobut.hawk.Hawk
import com.tevinjeffrey.rutgersct.data.database.PreferenceDao
import com.tevinjeffrey.rutgersct.data.database.UCTSubscriptionDao
import com.tevinjeffrey.rutgersct.data.model.Course
import com.tevinjeffrey.rutgersct.data.model.Section
import com.tevinjeffrey.rutgersct.data.model.Semester
import com.tevinjeffrey.rutgersct.data.model.Subject
import com.tevinjeffrey.rutgersct.data.model.University
import com.tevinjeffrey.rutgersct.data.notifications.SubscriptionManager
import com.tevinjeffrey.rutgersct.data.preference.DefaultSemester
import com.tevinjeffrey.rutgersct.data.preference.DefaultUniversity
import com.tevinjeffrey.rutgersct.data.search.UCTSubscription
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jonathanfinerty.once.Amount
import jonathanfinerty.once.Once
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class UCTApi @Inject constructor(
    private val uctService: UCTService,
    private val subscriptionManager: SubscriptionManager,
    private val subscriptionDao: UCTSubscriptionDao,
    private val preferenceDao: PreferenceDao) {

  companion object {
    private val TRACKED_SECTIONS = "trackedsections"
    private val TRACKED_SECTIONS_MIGRATION = "trackedsectionsmigration"
  }

  init {
    if (!Once.beenDone(TRACKED_SECTIONS_MIGRATION, Amount.exactly(1))) {
      val subscriptions = Hawk.get(TRACKED_SECTIONS, ArrayList<UCTSubscription>())

      Single.fromCallable({
        if (subscriptions.isNotEmpty()) {
          subscriptionDao.insertAll(subscriptions)
        }
        Single.just(true)
      })
          .observeOn(Schedulers.io())
          .subscribe(
              { Once.markDone(TRACKED_SECTIONS_MIGRATION) },
              { Timber.e(it) })
    }
  }


  var defaultSemester: Semester?
    get() {
      val defaultSemester = preferenceDao.defaultSemester()
      val semester = defaultSemester?.semester
      Timber.d("Getting semester: %s", semester)
      return semester
    }
    set(semester) {
      Timber.d("Setting semester: %s", semester)
      semester?.let {
        preferenceDao.updateDefaultSemester(DefaultSemester(semester))
      }
    }

  var defaultUniversity: University?
    get() {
      val defaultUniversity = preferenceDao.defaultUniversity()
      val university = defaultUniversity?.university
      Timber.d("Getting university: %s", university?.topic_name)
      return university
    }
    set(university) {
      Timber.d("Setting university: %s", university?.topic_name)
      university?.let {
        preferenceDao.updateDefaultUniversity(DefaultUniversity(university))
      }
    }

  fun universities(): Observable<List<University>> {
    return uctService.universities().map { response -> response.data!!.universities }
  }

  fun getCourses(topicName: String): Observable<List<Course>> {
    return uctService
        .getCourses(topicName)
        .map { response -> response.data!!.courses }
  }

  private fun getSection(topicName: String): Observable<Section> {
    return uctService
        .getSection(topicName)
        .map { response -> response.data!!.section!! }
        .onErrorResumeNext({ throwable: Throwable ->
          // Catch 404 exceptions
          if (throwable is HttpException) {
            if (throwable.code() == 404) {
              Observable.error(throwable)
            } else {
              Observable.empty<Section>()
            }
          }
          Observable.error(throwable)
        })
  }

  fun getSubjects(university: String, season: String, year: String): Observable<List<Subject>> {
    return uctService.getSubjects(university, season, year).map { response -> response.data!!.subjects }
  }

  fun getUniversity(universityTopicName: String): Observable<University> {
    return uctService.getUniversity(universityTopicName)
        .map { response -> response.data!!.university!! }
  }

  fun isTopicTracked(topicName: String): Boolean {
    return subscriptionDao.isSectionTracked(topicName)
  }

  fun refreshSubscriptions(): Observable<UCTSubscription> {
    return Observable.fromIterable(subscriptionDao.all())
        .flatMap { subscription ->
          getSection(
              subscription.sectionTopicName)
              .map { section -> subscription to section }
        }
        .map { pair ->
          val newUni = pair.first.university
          val newSub = UCTSubscription(pair.second.topic_name!!, newUni)
          newSub.university = newUni
          newSub
        }
        .toList()
        .flatMap { this.addAllSubscription(it) }
        .toObservable()
        .flatMap { Observable.fromIterable(subscriptionDao.all()) }
  }

  fun subscribeTo(subscription: UCTSubscription): Single<Boolean> {
    Timber.d("Subscribing to: %s", subscription)
    return Single.defer {
      try {
        subscriptionManager.subscribe(subscription.sectionTopicName)
      } catch (e: IOException) {
        return@defer Single.error<UCTSubscription>(e)
      }
      Single.just(subscription)
    }
        .subscribeOn(Schedulers.io())
        .flatMap { addSubscription(it) }
  }

  fun unsubscribeFrom(topicName: String): Single<Boolean> {
    Timber.d("Unsubscribing from: %s", topicName)
    return Single.defer {
      try {
        subscriptionManager.unsubscribe(topicName)
      } catch (e: IOException) {
        return@defer Single.error<String>(e)
      }
      Single.just(topicName)
    }
        .subscribeOn(Schedulers.io())
        .flatMap { removeSubscription(topicName) }
  }

  private fun addAllSubscription(subscription: List<UCTSubscription>): Single<Boolean> {
    return Single.defer {
      subscriptionDao.insertAll(subscription)
      Single.just(true)
    }
  }

  private fun addSubscription(subscription: UCTSubscription): Single<Boolean> {
    return Single.defer {
      subscriptionDao.insertAll(listOf(subscription))
      Single.just(true)
    }
  }

  private fun removeSubscription(topicName: String): Single<Boolean> {
    return Single.defer {
      subscriptionDao.deleteAll(topicName)
      Single.just(true)
    }
  }
}
