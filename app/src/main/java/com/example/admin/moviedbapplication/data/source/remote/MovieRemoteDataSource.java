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
    public void getMovies(MovieType type, int page, Callback<Category> callback) {
        String url = API.BASE_URL + API.MOVIE + API.SLASH +
                type.toString() + API.API_KEY + BuildConfig.ApiKey + API.PAGE + page;
        new MovieRemoteAsynTask(callback, type.toString()).execute(url);
    }

    @Override
    public void getMovies(Genre genre, Callback<List<Movie>> callback, int page) {
        String url = API.BASE_URL + API.DISCOVER + API.MOVIE + API.API_KEY +
                BuildConfig.ApiKey + API.GENRES + genre.getId() + API.PAGE + page;
        new GenreRemoteAsyntask(callback).execute(url);
    }
}
