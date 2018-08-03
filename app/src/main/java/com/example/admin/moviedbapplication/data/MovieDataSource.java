package com.example.admin.moviedbapplication.data;

import android.graphics.Movie;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/3/2018.
 */

public interface MovieDataSource {
    interface LoadMoviesCallback {

        void onMoviesLoaded(ArrayList<Movie> movies);

        void onDataNotAvailable();
    }

    void getMovies(@NonNull LoadMoviesCallback callback);
}
