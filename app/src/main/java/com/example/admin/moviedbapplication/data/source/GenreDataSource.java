package com.example.admin.moviedbapplication.data.source;

import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/6/2018.
 */

public interface GenreDataSource {
    void getGenres(Callback<ArrayList<Genre>> callback);
}
