package com.example.admin.moviedbapplication.screen.home;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.List;

/**
 * Created by TamTT on 8/7/2018.
 */

public interface HomeContract {
    interface View {

        void showCategory(List<Category> categories);

        void showLoadDataMainFail(Exception e);

        void showGenres(List<Genre> genres);
    }

    interface Presenter {

        void loadCategories();

        void loadGenres();
    }
}
