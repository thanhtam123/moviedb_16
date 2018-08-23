package com.example.admin.moviedbapplication.screen.favorites;

import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.List;

/**
 * Created by TamTT on 8/22/2018.
 */

public interface FavoritesContract {
    interface View {
        void displayFavMovies(List<Movie> movies);

        void updateData(String id);
    }

    interface Presenter {
        void loadListMovie();

        void deleteMovie(String id);
    }
}
