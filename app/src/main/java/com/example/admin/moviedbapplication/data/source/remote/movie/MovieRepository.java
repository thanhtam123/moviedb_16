package com.example.admin.moviedbapplication.data.source.remote.movie;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public class MovieRepository implements MovieDataSource.RemoteDataSource, MovieDataSource.LocalDataSourc {
    private static MovieRepository sInstance;
    @NonNull
    private MovieRemoteDataSource mMovieRemoteDataSource;

    private MovieLocalDataSource mMovieLocalDataSourc;

    private MovieRepository() {
    }

    private MovieRepository(MovieRemoteDataSource remoteDataSource,MovieLocalDataSource movieLocalDataSourc) {
       mMovieLocalDataSourc = movieLocalDataSourc;
       mMovieRemoteDataSource = remoteDataSource;
    }


    public static MovieRepository getInstance(MovieRemoteDataSource remoteDataSource,
                                              MovieLocalDataSource movieLocalDataSourc) {
        if (sInstance == null) {
            sInstance = new MovieRepository(remoteDataSource, movieLocalDataSourc);
        }
        return sInstance;
    }

    @Override
    public void getMovies(@MovieType String type, int page, Callback<Category> callback) {
        mMovieRemoteDataSource.getMovies(type, page, callback);
    }

    @Override
    public void getMovies(Genre genre, int page, Callback<List<Movie>> callback) {
        mMovieRemoteDataSource.getMovies(genre, page, callback);
    }

    @Override
    public void getMovies(int page, Callback<List<Movie>> callback) {
        mMovieRemoteDataSource.getMovies(page, callback);
    }

    @Override
    public void searchMoviesByName(int page, String name, Callback<List<Movie>> callback) {
        mMovieRemoteDataSource.searchMoviesByName(page, name, callback);
    }

    @Override
    public void getMovie(String id, Callback<Movie> callback) {
        mMovieRemoteDataSource.getMovie(id, callback);
    }

    @Override
    public void getMovieByActor(String actorId, Callback<List<Movie>> callback) {
        mMovieRemoteDataSource.getMovieByActor(actorId, callback);
    }

    @Override
    public void getListFavoritesMovie(ArrayList<String> arrayId, Callback<List<Movie>> callback) {
        mMovieRemoteDataSource.getListFavoritesMovie(arrayId,callback);
    }

    @Override
    public ArrayList<String> getListIdMovie(String key) {
        return mMovieLocalDataSourc.getListIdMovie(key);
    }

    @Override
    public void insertMovie(String key, String id) {
        mMovieLocalDataSourc.insertMovie(key, id);
    }

    @Override
    public void removeMovie(String key, String id) {
        mMovieLocalDataSourc.removeMovie(key, id);
    }
}
