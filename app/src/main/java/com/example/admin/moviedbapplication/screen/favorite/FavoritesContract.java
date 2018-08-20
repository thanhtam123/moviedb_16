package com.example.admin.moviedbapplication.screen.favorite;

import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/12/2018.
 */

public interface FavoritesContract {
    interface View{
        void showListMovie(ArrayList<Movie> arrayList);

        void showListMovieFail(Exception e);

        void showAnimationLoading();

        void dimissAnimationLoading();
    }

    interface Presenter{

        void loadMovies(ArrayList<String> idMovies);

        ArrayList<String> loadFavoriteMovieIdInLocal();

        void removeMovie(String id);
    }
}
