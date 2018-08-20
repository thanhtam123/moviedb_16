package com.example.admin.moviedbapplication.screen.favorite;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.SharedPrefsKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/13/2018.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter{

    private MovieRepository mMovieRepository;
    private FavoritesContract.View mFavoritesView;

    public FavoritesPresenter(FavoritesContract.View favoritesView, MovieRepository movieRepository) {
        mFavoritesView = favoritesView;
        mMovieRepository = movieRepository;
    }

    @Override
    public void loadMovies(ArrayList<String> idMovies) {
        mMovieRepository.getListFavoritesMovie(idMovies, new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                mFavoritesView.showListMovie(new ArrayList<>(data));
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mFavoritesView.showListMovieFail(e);
            }
        });
    }

    @Override
    public ArrayList<String> loadFavoriteMovieIdInLocal() {
        return mMovieRepository.getListIdMovie(SharedPrefsKey.FAVORITES_LIST_KEY);
    }

    @Override
    public void removeMovie(String id) {
        mMovieRepository.removeMovie(SharedPrefsKey.FAVORITES_LIST_KEY, id);
    }

}
