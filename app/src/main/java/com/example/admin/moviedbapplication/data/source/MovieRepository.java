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

public class MovieRepository implements MovieDataSource{
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
    public void getMovies(@MovieType String type, int page, Callback<Category> callback) {
        mRemoteDataSource.getMovies(type, page, callback);
    }

    @Override
    public void getMovies(Genre genre, int page, Callback<List<Movie>> callback) {
        mRemoteDataSource.getMovies(genre, page, callback);
    }

    @Override
    public void getMovies(int page, Callback<List<Movie>> callback) {
        mRemoteDataSource.getMovies(page, callback);
    }

    @Override
    public void searchMoviesByName(int page, String name, Callback<List<Movie>> callback) {
        mRemoteDataSource.searchMoviesByName(page, name, callback);
    }

    @Override
    public void getMovie(String id, Callback<Movie> callback) {
        mRemoteDataSource.getMovie(id, callback);
    }

    @Override
    public void getMovieByActor(String actorId, Callback<List<Movie>> callback) {
        mRemoteDataSource.getMovieByActor(actorId, callback);
    }
}
