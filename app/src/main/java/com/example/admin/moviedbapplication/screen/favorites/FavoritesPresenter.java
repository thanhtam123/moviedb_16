package com.example.admin.moviedbapplication.screen.favorites;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieRepository;

import java.util.List;

/**
 * Created by TamTT on 8/22/2018.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter {

    private MovieRepository mMovieRepository;
    private FavoritesContract.View mView;

    public FavoritesPresenter(FavoritesContract.View view, MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        mView = view;
    }

    @Override
    public void loadListMovie() {
        mMovieRepository.getAllMovies(new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                mView.displayFavMovies(data);
            }

            @Override
            public void onGetDataFailure(Exception e) {

            }
        });
    }

    @Override
    public void deleteMovie(String id) {
        int rowEffect = mMovieRepository.deleteMovie(id);
        if (rowEffect == 1) {
            mView.updateData(id);
        }
    }
}
