package com.example.admin.moviedbapplication.screen.search;

import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/9/2018.
 */

public interface SearchContract {
    interface View {
        void onGetMovieSuccess(ArrayList<Movie> movies);

        void onGetMoviesFailure(Exception e);

        void showNoResult();

        void clearData();

        void addLoadingIndicator();
    }

    interface Presenter {
        void loadSearchMovie(int page, String name);
    }
}
