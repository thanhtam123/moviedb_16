package com.example.admin.moviedbapplication.screen.detail;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.Video;

import java.util.List;

/**
 * Created by TamTT on 8/10/2018.
 */

public class DetailContract {

    interface View{

        void showMovie(Movie movie);

        void showVideoLoadFail(String e);

        void playVideo(Video video);

        void getFavorites(List<Movie> movies);

        void updateFavoritesButton(boolean isFavorite);

        void updateFavoritesListFailure();
    }

    interface Presenter{

        void loadVideo(String id);

        void addFavorites(Movie movie);

        void removeFavorites(String id);

        void loadListFavorites();

        boolean checkFavorite(String idMovie);
    }
}
