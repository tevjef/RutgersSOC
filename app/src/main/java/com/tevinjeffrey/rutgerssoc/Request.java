package com.tevinjeffrey.rutgerssoc;

import android.os.Parcel;
import android.os.Parcelable;

import com.tevinjeffrey.rutgerssoc.utils.UrlUtils;

import java.util.ArrayList;

/**
 * Created by Tevin on 1/22/2015.
 */
public class Request implements Parcelable {

    String subject;
    String semester;
    ArrayList<String> locations;
    ArrayList<String> levels;

    public Request(String subject, String semester, ArrayList<String> locations, ArrayList<String> levels) {
        this.subject = subject;
        this.semester = semester;
        this.locations = locations;
        this.levels = levels;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<String> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<String> levels) {
        this.levels = levels;
    }
    public boolean isCourseRequest() {
        return getSubject() != null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subject);
        dest.writeString(this.semester);
        dest.writeSerializable(this.locations);
        dest.writeSerializable(this.levels);
    }

    private Request(Parcel in) {
        this.subject = in.readString();
        this.semester = in.readString();
        this.locations = (ArrayList<String>) in.readSerializable();
        this.levels = (ArrayList<String>) in.readSerializable();
    }

    public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
        public Request createFromParcel(Parcel source) {
            return new Request(source);
        }

        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    @Override
    public String toString() {
        UrlUtils urlUtils = new UrlUtils();
        return urlUtils.buildParamUrl(this);
    }
}
