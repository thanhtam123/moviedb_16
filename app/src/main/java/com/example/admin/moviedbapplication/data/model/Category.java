package com.example.admin.moviedbapplication.data.model;

/**
 * Created by TamTT on 8/6/2018.
 */

public class Category {
    private String mId;
    private String mCategeryName;

    public Category(String id, String categeryName) {
        mId = id;
        mCategeryName = categeryName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCategeryName() {
        return mCategeryName;
    }

    public void setCategeryName(String categeryName) {
        mCategeryName = categeryName;
    }
}
