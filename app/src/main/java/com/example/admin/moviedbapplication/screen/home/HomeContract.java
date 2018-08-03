package com.example.admin.moviedbapplication.screen.home;

import com.example.admin.moviedbapplication.BaseView;
import com.example.admin.moviedbapplication.model.CategoryMovie;
import com.example.admin.moviedbapplication.model.Genres;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/3/2018.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showListCategoryMovies(ArrayList<CategoryMovie> categoryMovies);

        void showBanners(ArrayList<String> arrayBanner);

        void showGenres(ArrayList<Genres> genresArrayList);

        void showDialogLoadDataFromServer();

        boolean isActive();
    }

    interface Presenter{
        void loadListCategoryMovies();

        void loadBanners();

        void loadGenres();

        void loadData();

    }
}
