package com.example.admin.moviedbapplication.data.source.remote;

import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.GenreDataSource;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/9/2018.
 */

public class GenreRemoteDataSource implements GenreDataSource {

    private static GenreRemoteDataSource sInstance;

    private GenreRemoteDataSource() {
    }

    public static GenreRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GenreRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getGenres(Callback<ArrayList<Genre>> callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API.BASE_URL)
                .append(API.GENRE_LIST)
                .append(API.API_KEY)
                .append(BuildConfig.ApiKey);
        String url = stringBuilder.toString();
        new GenresRemoteAsyntask(callback).execute(url);
    }
}
