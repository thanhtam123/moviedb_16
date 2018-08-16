package com.example.admin.moviedbapplication.data.model;

/**
 * Created by TamTT on 8/12/2018.
 */

public class Video {
    private String mId;
    private String mKey;

    public Video(String id, String key) {
        mId = id;
        mKey = key;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    @Override
    public String toString() {
        return "Video{" +
                "mId='" + mId + '\'' +
                ", mKey='" + mKey + '\'' +
                '}';
    }

    public interface JsonVideoKey {
        String RESULTS = "results";
        String KEY = "key";
        String ID = "id";
    }
}
