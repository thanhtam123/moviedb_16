package com.example.admin.moviedbapplication.data.source.remote;

import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieDataSource;

import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public class MovieRemoteDataSource implements MovieDataSource {
    private static MovieRemoteDataSource sInstance;

    private MovieRemoteDataSource() {
    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getMovies(MovieType type, int page, Callback<List<Movie>> callback) {
        new MovieRemoteAsynTask(callback).execute();
    }

    @Override
    public void getMovies(Genre genre, Callback<List<Movie>> callback) {

    }
}
