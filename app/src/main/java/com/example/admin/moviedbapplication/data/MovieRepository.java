package com.example.admin.moviedbapplication.data;

import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/3/2018.
 */

public class MovieRepository implements MovieDataSource{
    private static MovieRepository INSTANCE = null;
    private MovieDataSource mMoviesRemoteDataSource;

    private MovieRepository(@NonNull MovieDataSource moviesRemoteDataSource) {
        mMoviesRemoteDataSource = moviesRemoteDataSource;
    }

    public static MovieRepository getInstance(MovieDataSource moviesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(moviesRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMovies(@NonNull LoadMoviesCallback callback) {
        getMoviesFromServer(callback);
    }

    private void getMoviesFromServer(LoadMoviesCallback callback) {
        mMoviesRemoteDataSource.getMovies(new LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(ArrayList<Movie> movies) {
                Log.e("TAG",""+ movies.size());
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("TAG","load Fail");
            }
        });
    }


}
