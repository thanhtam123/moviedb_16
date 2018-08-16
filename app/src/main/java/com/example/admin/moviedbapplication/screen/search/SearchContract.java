package com.example.admin.moviedbapplication.screen.search;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/9/2018.
 */

public interface SearchContract {
    interface View{

        void showListPopularMovie(ArrayList<Movie> movies);

        void updateListSearch(ArrayList<Movie> movies, int page);

        void showListMovieLoadFail(Exception e);

        void showAnimationLoading();

        boolean isActive();

    }
    interface Presenter{

        void loadSearchMovie(int page, String name);

        void loadRecommendMovies(@MovieType String type, int page);
    }
}
