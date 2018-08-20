package com.example.admin.moviedbapplication.data.source.local;

import com.example.admin.moviedbapplication.data.source.Callback;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/19/2018.
 */

public interface SharePrefsApi {
    ArrayList<String> get(String key);

    void put(String key, String idMovies);

    void remove(String key, String idMovies);

    void clear(Callback<Boolean> callback);
}
