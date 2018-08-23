package com.example.admin.moviedbapplication.screen.genre;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/11/2018.
 */

public class GenrePresenter implements GenreContract.Presenter {

    private MovieRepository mMovieRepository;
    private GenreContract.View mGenreView;

    public GenrePresenter(GenreContract.View genreView, MovieRepository movieRepository) {
        mGenreView = genreView;
        mMovieRepository = movieRepository;
    }

    @Override
    public void loadGenres(Genre genre, int page) {
        mMovieRepository.getMovies(genre, page, new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                mGenreView.showGenres(new ArrayList<>(data));
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mGenreView.showGenresFail(e);
            }
        });
    }

    @Override
    public void loadCategories(int page, String type) {
        mMovieRepository.getMovies(type, page, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mGenreView.showGenres(new ArrayList<>(data.getCategoryMovie()));
            }
            @Override
            public void onGetDataFailure(Exception e) {
                mGenreView.showGenresFail(e);
            }
        });
    }
}
