package com.example.admin.moviedbapplication.data.source;

import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public interface GenresDataSource {
    void getGenres(Callback<List<Genre>> callback);
}
