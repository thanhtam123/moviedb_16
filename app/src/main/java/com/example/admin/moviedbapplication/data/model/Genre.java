package com.example.admin.moviedbapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TamTT on 8/6/2018.
 */

public class Genre implements Parcelable{
    private String mId;
    private String mGenreName;

    public Genre(String id, String genreName) {
        mId = id;
        mGenreName = genreName;
    }

    protected Genre(Parcel in) {
        mId = in.readString();
        mGenreName = in.readString();
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getGenreName() {
        return mGenreName;
    }

    public void setGenreName(String genreName) {
        mGenreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "mId='" + mId + '\'' +
                ", mGenreName='" + mGenreName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mGenreName);
    }
}
