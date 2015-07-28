package com.tevinjeffrey.rutgersct.testUtils;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;
import com.tevinjeffrey.rutgersct.database.TrackedSection;
import com.tevinjeffrey.rutgersct.rutgersapi.model.Request;
import com.tevinjeffrey.rutgersct.rutgersapi.utils.SemesterUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import timber.log.Timber;

public class RutgersApiConts {

    public static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            com.squareup.okhttp.Request originalRequest = chain.request();
            Timber.d("Host: %s", originalRequest.httpUrl().host());

            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=10")
                    .build();
        }
    };

    List<TrackedSection> trackedSections;

    SemesterUtils semesterUtils = new SemesterUtils(Calendar.getInstance());

    final SemesterUtils.Semester SEMESTER = semesterUtils.resolveCurrentSemester();

    final String YEAR = SEMESTER.getYear();
    final String SEASON = SEMESTER.getSeason().getName();

    final TrackedSection t1 = new TrackedSection("011", SEASON + " " + YEAR, "Newark", "Undergraduate", "19961");
    final TrackedSection t2 = new TrackedSection("014", SEASON + " " + YEAR, "Newark", "Undergraduate", "07495");
    final TrackedSection t3 = new TrackedSection("049", SEASON + " " + YEAR, "Newark", "Undergraduate", "13927");
    final TrackedSection t4 = new TrackedSection("510", SEASON + " " + YEAR, "Newark", "Undergraduate", "19173");
    final TrackedSection t5 = new TrackedSection("510", SEASON + " " + YEAR, "Newark", "Undergraduate", "19172");
    final TrackedSection t6 = new TrackedSection("011", SEASON + " " + YEAR, "Newark", "Undergraduate", "01842");

    final Request requestNewark =
            new Request("010",
                    SEMESTER,
                    new ArrayList<>(Arrays.asList(new String[]{"Newark"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    final Request requestBrunswick =
            new Request("010",
                    SEMESTER,
                    new ArrayList<>(Arrays.asList(new String[]{"New Bruswick"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    final Request requestCamden =
            new Request("010",
                    SEMESTER,
                    new ArrayList<>(Arrays.asList(new String[]{"Camden"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    final Request requestAllFall =
            new Request("010",
                    //Current year - 1 because not all semesters of the current year will be available.
                    new SemesterUtils.Semester(SemesterUtils.Season.FALL, String.valueOf(Integer.valueOf(YEAR) - 1)),
                    new ArrayList<>(Arrays.asList(new String[]{"Newark", "New Brunswick", "Camden"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    final Request requestAllWinter =
            new Request("010",
                    new SemesterUtils.Semester(SemesterUtils.Season.WINTER, String.valueOf(Integer.valueOf(YEAR) - 1)),
                    new ArrayList<>(Arrays.asList(new String[]{"Newark", "New Brunswick", "Camden"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    final Request requestAllSpring =
            new Request("010",
                    new SemesterUtils.Semester(SemesterUtils.Season.SPRING, String.valueOf(Integer.valueOf(YEAR) - 1)),
                    new ArrayList<>(Arrays.asList(new String[]{"Newark", "New Brunswick", "Camden"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    final Request requestAllSummer =
            new Request("010",
                    new SemesterUtils.Semester(SemesterUtils.Season.SUMMER, String.valueOf(Integer.valueOf(YEAR) - 1)),
                    new ArrayList<>(Arrays.asList(new String[]{"Newark", "New Brunswick", "Camden"})),
                    new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));

    public RutgersApiConts() {
        this.semesterUtils = new SemesterUtils(Calendar.getInstance());
    }

    public Request getPrimarySemesterRequest() {
        return new Request("198",
                semesterUtils.resolvePrimarySemester(),
                new ArrayList<>(Arrays.asList(new String[]{"Newark", "New Brunswick", "Camden"})),
                new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    }

    public Request getSecondarySemesterRequest() {
        return new Request("198",
                semesterUtils.resolveSecondarySemester(),
                new ArrayList<>(Arrays.asList(new String[]{"Newark", "New Brunswick", "Camden"})),
                new ArrayList<>(Arrays.asList(new String[]{"Undergraduate", "Graduate"})));
    }

    public List<TrackedSection> createTrackedSections() {
        trackedSections = new ArrayList<>();
        trackedSections.add(t1);
        trackedSections.add(t2);
        trackedSections.add(t3);
        trackedSections.add(t4);
        trackedSections.add(t5);
        trackedSections.add(t6);
        return trackedSections;
    }

    @Override
    public String toString() {
        return "RutgersApiTestConts{" +
                "SEMESTER=" + SEMESTER +
                '}';
    }
}