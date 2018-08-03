package com.example.admin.moviedbapplication.model;

/**
 * Created by TamTT on 8/3/2018.
 */

public class Genres {
    private String mId;
    private String mName;

    public Genres(String mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
