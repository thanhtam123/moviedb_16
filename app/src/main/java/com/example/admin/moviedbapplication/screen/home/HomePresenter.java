package com.example.admin.moviedbapplication.screen.home;

import com.example.admin.moviedbapplication.data.MovieRepository;

/**
 * Created by TamTT on 8/4/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private MovieRepository mMoviesRepository;

    private HomeContract.View mMovieView;

    private boolean isLoaded = false;
    public HomePresenter(MovieRepository mTasksRepository, HomeContract.View mMoviesView) {
        this.mMoviesRepository = mTasksRepository;
        this.mMovieView = mMoviesView;

        mMoviesView.setPresenter(this);
    }

    @Override
    public void loadListCategoryMovies() {

    }

    @Override
    public void loadBanners() {

    }

    @Override
    public void loadGenres() {

    }

    @Override
    public void loadData() {
        if(!isLoaded){
            loadListCategoryMovies();
            loadBanners();
            loadGenres();
        }else {

        }
    }
}
