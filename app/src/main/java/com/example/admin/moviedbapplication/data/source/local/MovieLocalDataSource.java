package com.example.admin.moviedbapplication.data.source.local;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieDataSource;

import java.util.List;

/**
 * Created by TamTT on 8/21/2018.
 */

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource{

    private static MovieLocalDataSource sInstance;

    private MovieDao mMovieDao;

    public MovieLocalDataSource(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    public static MovieLocalDataSource getinstance (MovieDao movieDao){
        if(sInstance == null){
            sInstance = new MovieLocalDataSource(movieDao);
        }
        return sInstance;
    }
    @Override
    public void getAllMovies(Callback<List<Movie>> callback) {
        List<Movie> movies = mMovieDao.getAllMovies();
        if(movies != null){
            callback.onGetDataSuccess(movies);
        }else {
            callback.onGetDataFailure(null);
        }
    }

    @Override
    public int deleteMovie(String idMovie) {
        return mMovieDao.deleteMovie(idMovie);
    }

    @Override
    public long insertMovies(Movie movie) {
        return mMovieDao.insertMovie(movie);
    }
}
