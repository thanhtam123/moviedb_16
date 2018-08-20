package com.example.admin.moviedbapplication.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by TamTT on 8/19/2018.
 */

public class SharedPreferenceImplement implements SharePrefsApi{
    private static final String PREFS_NAME = "ProjectOneMovieDB";

    private SharedPreferences mSharedPreferences;

    public SharedPreferenceImplement(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public ArrayList<String> get(String key) {
        String s = mSharedPreferences.getString(key, "");
        ArrayList<String> arrayList = new ArrayList<>();
        if(!Objects.equals(s, "")){
            String[] aStrings = s.split(",");
            for (String a: aStrings) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    @Override
    public void put(String key, String idMovies) {
        ArrayList<String> list = null;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        list = get(key);
        if (list == null) list = new ArrayList<>();
        list.add(idMovies);
        editor.putString(key, Utils.parseArrayToString(list));
        editor.apply();
    }

    @Override
    public void remove(String key, String idMovies) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        ArrayList<String> list = null;
        list = get(key);
        if (list == null) list = new ArrayList<>();
        list.remove(idMovies);
        editor.putString(key, Utils.parseArrayToString(list));
        editor.apply();
    }

    @Override
    public void clear(Callback<Boolean> callback) {

    }
}
