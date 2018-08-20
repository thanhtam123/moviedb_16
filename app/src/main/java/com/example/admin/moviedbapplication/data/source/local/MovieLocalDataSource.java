package com.example.admin.moviedbapplication.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.source.remote.movie.MovieDataSource;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/19/2018.
 */

public class MovieLocalDataSource implements MovieDataSource.LocalDataSourc {

    private SharedPreferenceImplement mPrefImpl;

    private static MovieLocalDataSource instance;
    private MovieLocalDataSource(Context context) {
        mPrefImpl = new SharedPreferenceImplement(context);
    }

    public static synchronized MovieLocalDataSource getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new MovieLocalDataSource(context);
        }
        return instance;
    }

    @Override
    public ArrayList<String> getListIdMovie(String key) {
        return mPrefImpl.get(key);
    }

    @Override
    public void insertMovie(String key, String id) {
        mPrefImpl.put(key, id);
    }

    @Override
    public void removeMovie(String key, String id) {
        mPrefImpl.remove(key, id);
    }
}
