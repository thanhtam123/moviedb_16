package com.example.admin.moviedbapplication.utils;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/11/2018.
 */

public class DataClass {
    private static ArrayList<Genre> mGenres = new ArrayList<>();

    @NonNull
    public static String getData(int a, int b) {
        ArrayList<String> arrayList = new ArrayList<>();
        for(int j = 0; j<mGenres.size(); j++){
            if(mGenres.get(j).getId().equals(String.valueOf(a)) ||
                    mGenres.get(j).getId().equals((String.valueOf(b)))){
                arrayList.add(mGenres.get(j).getGenreName());
            }
        }
        return arrayList.get(0)+", "+arrayList.get(1);
    }

    public static void setData(ArrayList<Genre> data) {
        DataClass.mGenres = data;
    }
}
