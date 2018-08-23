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

public class MovieRepository implements MovieDataSource.RemoteDataSource,
        MovieDataSource.LocalDataSource {
    private static MovieRepository sInstance;
    @NonNull
    private MovieDataSource.RemoteDataSource mRemoteDataSource;
    private MovieDataSource.LocalDataSource mLocalDataSource;

    private MovieRepository(MovieDataSource.RemoteDataSource remoteDataSource,
                            MovieDataSource.LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static MovieRepository getInstance(
            @NonNull MovieDataSource.RemoteDataSource remoteDataSource,
            MovieDataSource.LocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(remoteDataSource, localDataSource);
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

    @Override
    public void getAllMovies(Callback<List<Movie>> callback) {
        mLocalDataSource.getAllMovies(callback);
    }

    @Override
    public int deleteMovie(String idMovie) {
        return mLocalDataSource.deleteMovie(idMovie);
    }

    @Override
    public long insertMovies(Movie movie) {
        return mLocalDataSource.insertMovies(movie);
    }

    @Override
    public Movie isFavorites(String idMovie) {
        return mLocalDataSource.isFavorites(idMovie);
    }
}
