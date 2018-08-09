package com.example.admin.moviedbapplication.screen.home;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public interface HomeContract {
    interface View {

        void showCategory(ArrayList<Category> tasks);

        void showLoadDataMainFail(Exception e);

        void showGenres(ArrayList<Genre> genres);

        void showBanner(ArrayList<Movie> movies);

        void showLoadingAnimation();

    }

    interface Presenter {

        void loadCategories(int page);

        void loadBanners();

        void loadGenres();

        void openMovieDetails(@NonNull Movie movie);
    }
}
