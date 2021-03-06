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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API.BASE_URL)
                .append(API.MOVIE )
                .append(API.SLASH)
                .append(type)
                .append(API.API_KEY)
                .append(BuildConfig.ApiKey)
                .append(API.PAGE).append(page);
        String urlPopular = stringBuilder.toString();
        new MovieRemoteAsynTask(callback, type).execute(urlPopular);
    }

    @Override
    public void getMovies(Genre genre, int page, Callback<List<Movie>> callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API.BASE_URL)
                .append(API.DISCOVER)
                .append(API.MOVIE)
                .append(API.API_KEY)
                .append(BuildConfig.ApiKey)
                .append(API.GENRES)
                .append(genre.getId())
                .append(API.PAGE)
                .append(page);
        String url = stringBuilder.toString();
        new MovieByGenreRemoteAsyntask(callback).execute(url);
    }

    @Override
    public void getMovies(int page, Callback<List<Movie>> callback) {
        /*String url = API.BASE_URL + API.DISCOVER + API.MOVIE + API.API_KEY +
                BuildConfig.ApiKey + API.GENRES + genre.getId() + API.PAGE + page;*/
    }

    @Override
    public void searchMoviesByName(int page, String name, Callback<List<Movie>> callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API.BASE_URL)
                .append(API.SEARCH)
                .append(API.MOVIE )
                .append(API.API_KEY)
                .append(BuildConfig.ApiKey)
                .append(API.QUERY )
                .append(name).append(API.PAGE)
                .append(page);
        String url = stringBuilder.toString();
        new MovieByGenreRemoteAsyntask(callback).execute(url);
    }

    @Override
    public void getMovie(String id, Callback<Movie> callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API.BASE_URL)
                .append(API.MOVIE)
                .append(API.SLASH)
                .append(id)
                .append(API.API_KEY)
                .append(BuildConfig.ApiKey);
        String url = stringBuilder.toString();
        new SingleMovieRemoteAsyntask(callback).execute(url);
    }
}
