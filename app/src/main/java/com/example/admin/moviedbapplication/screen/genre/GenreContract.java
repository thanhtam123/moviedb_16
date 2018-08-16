package com.example.admin.moviedbapplication.screen.genre;

import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/11/2018.
 */

public interface GenreContract {
    interface View{
        void showGenres(ArrayList<Movie> genres);

        void showGenresFail(Exception e);

        void showAnimationLoading();

        void initRecyclerView();
    }

    interface Presenter{

        void loadGenres(Genre genre, int page);

        void loadCategories(int page, @MovieType String type);
    }
}
