package com.example.admin.moviedbapplication.screen.search;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/9/2018.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private MovieRepository mMovieRepository;
    private SearchContract.View mViewSearch;

    public SearchPresenter(SearchContract.View viewSearch, MovieRepository movieRepository) {
        mViewSearch = viewSearch;
        mMovieRepository = movieRepository;
    }

    @Override
    public void loadSearchMovie(int page, String name) {
        mMovieRepository.searchMoviesByName(page, name, new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                mViewSearch.updateListSearch(new ArrayList<>(data));
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mViewSearch.showListMovieLoadFail(e);
            }
        });
    }

    @Override
    public void loadRecommendMovies(String type, int page) {
        mMovieRepository.getMovies(type, page, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mViewSearch.showListPopularMovie(data.getCategoryMovie());
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mViewSearch.showListMovieLoadFail(e);
            }
        });
    }
}
