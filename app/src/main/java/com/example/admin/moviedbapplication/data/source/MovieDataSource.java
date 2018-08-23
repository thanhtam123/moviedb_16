package com.example.admin.moviedbapplication.data.source;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;

import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public interface MovieDataSource {
    interface RemoteDataSource {
        /**
         * Load movie from server
         *
         * @param type     : movie type like now playing, top rate, ....
         * @param page     : current page
         * @param callback : callback
         */
        void getMovies(@MovieType String type, int page, Callback<Category> callback);

        /**
         * Get moives by genre
         *
         * @param genre
         * @param callback
         */
        void getMovies(Genre genre, int page, Callback<List<Movie>> callback);

        void getMovies(int page, Callback<List<Movie>> callback);

        void searchMoviesByName(int page, String name, Callback<List<Movie>> callback);

        void getMovie(String id, Callback<Movie> callback);

        void getMovieByActor(String actorId, Callback<List<Movie>> callback);
    }

    interface LocalDataSource {
        void getAllMovies(Callback<List<Movie>> callback);

        int deleteMovie(String idMovie);

        long insertMovies(Movie movie);

        Movie isFavorites(String idMovie);
    }
}
