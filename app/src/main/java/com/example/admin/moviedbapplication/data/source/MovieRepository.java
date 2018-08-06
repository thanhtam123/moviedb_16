package com.example.admin.moviedbapplication.data.source;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;

import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public class MovieRepository implements MovieDataSource {
    private static MovieRepository sInstance;
    @NonNull
    private MovieDataSource mRemoteDataSource;

    private MovieRepository() {
    }

    private MovieRepository(MovieDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(@NonNull MovieDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getMovies(MovieType type, int page, Callback<Category> callback) {
        mRemoteDataSource.getMovies(type, page, callback);
    }

    @Override
    public void getMovies(Genre genre, Callback<List<Movie>> callback) {
        mRemoteDataSource.getMovies(genre, callback);
    }
}
