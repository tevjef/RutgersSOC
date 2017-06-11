package com.tevinjeffrey.rutgersct.data.preference;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.tevinjeffrey.rutgersct.data.model.Semester;
import com.tevinjeffrey.rutgersct.data.model.University;

@Entity(tableName = "default_semester")
public class DefaultSemester {

  @PrimaryKey
  private Semester semester;

  public DefaultSemester(final Semester semester) {
    this.semester = semester;
  }

  public Semester getSemester() {
    return semester;
  }

  public void setSemester(final Semester semester) {
    this.semester = semester;
  }
}
