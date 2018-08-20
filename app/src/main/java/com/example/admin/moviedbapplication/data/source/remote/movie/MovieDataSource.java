package com.example.admin.moviedbapplication.data.source.remote.movie;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public interface MovieDataSource {

    interface RemoteDataSource {
        /**
         * Load movie from server
         * @param type          : movie type like now playing, top rate, ....
         * @param page          : current page
         * @param callback      : callback
         */
        void getMovies(@MovieType String type, int page, Callback<Category> callback);

        /**
         * Get moives by genre
         * @param genre
         * @param callback
         */
        void getMovies(Genre genre, int page, Callback<List<Movie>> callback);

        void getMovies(int page, Callback<List<Movie>> callback);

        void searchMoviesByName(int page, String name, Callback<List<Movie>> callback);

        void getMovie(String id, Callback<Movie> callback);

        void getMovieByActor(String actorId, Callback<List<Movie>> callback);

        void getListFavoritesMovie(ArrayList<String> arrayId, Callback<List<Movie>> callback);
    }

    interface LocalDataSourc {
        ArrayList<String> getListIdMovie(String key);

        void insertMovie(String key, String id);

        void removeMovie(String key, String id);
    }
}
