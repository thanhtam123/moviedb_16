package com.example.admin.moviedbapplication.data.source.remote;

import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieDataSource;

import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public class MovieRemoteDataSource implements MovieDataSource {
    private static MovieRemoteDataSource sInstance;

    private MovieRemoteDataSource() {
    }

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getMovies(@MovieType String type, int page, Callback<Category> callback) {
        String urlPopular = API.BASE_URL + API.MOVIE + API.SLASH + type +
                API.API_KEY + BuildConfig.ApiKey + API.PAGE + page;
        new MovieRemoteAsynTask(callback, type).execute(urlPopular);
    }

    @Override
    public void getMovies(Genre genre, int page, Callback<List<Movie>> callback) {
        String url = API.BASE_URL + API.DISCOVER + API.MOVIE + API.API_KEY +
                BuildConfig.ApiKey + API.GENRES + genre.getId() + API.PAGE + page;
        new MovieByGenreRemoteAsyntask(callback).execute(url);
    }

    @Override
    public void getMovies(int page, Callback<List<Movie>> callback) {
        /*String url = API.BASE_URL + API.DISCOVER + API.MOVIE + API.API_KEY +
                BuildConfig.ApiKey + API.GENRES + genre.getId() + API.PAGE + page;*/
    }

    @Override
    public void searchMoviesByName(int page, String name, Callback<List<Movie>> callback) {
        String url = API.BASE_URL + API.SEARCH + API.MOVIE + API.API_KEY +
                BuildConfig.ApiKey + API.QUERY + name;
        new MovieByGenreRemoteAsyntask(callback).execute(url);
    }
}
