package com.example.admin.moviedbapplication.data.source.remote.genre;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.source.Callback;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/8/2018.
 */

public class GenreRepository implements GenreDataSource {
    private static GenreRepository sInstance;
    @NonNull
    private GenreDataSource mGenreDataSource;

    private GenreRepository() {
    }

    private GenreRepository(GenreDataSource localDataSource) {
        mGenreDataSource = localDataSource;
    }

    public static GenreRepository getInstance(@NonNull GenreDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(localDataSource);
        }
        return sInstance;
    }

    @Override
    public void getGenres(Callback<ArrayList<Genre>> callback) {
        mGenreDataSource.getGenres(callback);
    }
}
