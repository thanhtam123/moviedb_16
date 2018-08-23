package com.example.admin.moviedbapplication.screen.search;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.moviedbapplication.screen.search.SearchFragment.FIRST_PAGE;

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
    public void loadSearchMovie(int page, final String name) {
        if (page == FIRST_PAGE){
            mViewSearch.clearData();
        }
        mViewSearch.addLoadingIndicator();
        mMovieRepository.searchMoviesByName(page, name, new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                if(data == null){
                    mViewSearch.showNoResult();
                }
                else{
                    mViewSearch.onGetMovieSuccess((ArrayList<Movie>) data);
                }
            }
            @Override
            public void onGetDataFailure(Exception e) {
                mViewSearch.onGetMoviesFailure(e);
            }
        });
    }
}
