package com.example.admin.moviedbapplication.data.model;

/**
 * Created by TamTT on 8/6/2018.
 */

public class Genre {
    private String mId;
    private String mGenreName;

    public Genre(String id, String genreName) {
        mId = id;
        mGenreName = genreName;
    }

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
}
