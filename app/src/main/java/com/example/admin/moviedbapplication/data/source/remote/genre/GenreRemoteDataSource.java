package com.example.admin.moviedbapplication.data.source.remote.genre;

import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.API;

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
        String url = API.BASE_URL + API.GENRE_LIST + API.API_KEY + BuildConfig.ApiKey;
        new GenresRemoteAsyntask(callback).execute(url);
    }
}
