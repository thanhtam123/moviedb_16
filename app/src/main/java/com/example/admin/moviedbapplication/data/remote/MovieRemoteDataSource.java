package com.example.admin.moviedbapplication.data.remote;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.MovieDataSource;

/**
 * Created by TamTT on 8/3/2018.
 */

public class MovieRemoteDataSource implements MovieDataSource{

    private static MovieRemoteDataSource INSTANCE;

    public static MovieRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieRemoteDataSource();
        }
        return INSTANCE;
    }

    private MovieRemoteDataSource() {}

    @Override
    public void getMovies(@NonNull LoadMoviesCallback callback) {
        
    }
}
