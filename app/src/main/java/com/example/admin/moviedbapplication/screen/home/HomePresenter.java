package com.example.admin.moviedbapplication.screen.home;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.GenreRepository;
import com.example.admin.moviedbapplication.data.source.MovieRepository;
import com.example.admin.moviedbapplication.data.source.remote.GenreRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/7/2018.
 */

public class HomePresenter implements HomeContract.Presenter {
    /**
     * load only one page
     */
    private static final int FIRST_PAGE = 1;

    private MovieRepository mMovieRepository;
    private GenreRepository mGenreRepository;
    private HomeContract.View mViewHome;
    private List<Genre> mGenres;
    private List<Category> mCategories;
    private int mCountRunningAsyn;

    public HomePresenter(HomeContract.View viewHome, MovieRepository movieRepository) {
        mViewHome = viewHome;
        mMovieRepository = movieRepository;
        mGenreRepository = GenreRepository.getInstance(GenreRemoteDataSource.getInstance());
        mCategories = new ArrayList<>();
        mGenres = new ArrayList<>();
    }

    /**
     * get movie by type
     *
     * @param movieType
     */
    private void getMovies(@MovieType String movieType) {
        mCountRunningAsyn++;
        mMovieRepository.getMovies(movieType, FIRST_PAGE, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mCategories.add(data);
                onLoadDataSuccess();
            }

            @Override
            public void onGetDataFailure(Exception e) {
                onLoadDataFailure(e);
            }
        });
    }

    /**
     * Show all data if load movie and genre finished
     */
    private void onLoadDataSuccess() {
        decreaseCount();
        onFinishLoading();
    }

    /**
     * Show error and show all data if load movie and genre finished
     * @param e
     */
    private void onLoadDataFailure(Exception e) {
        decreaseCount();
        mViewHome.showLoadDataMainFail(e);
        onFinishLoading();
    }

    /**
     * decrease count when load asyntask finished loading
     */
    private void decreaseCount() {
        mCountRunningAsyn--;
    }

    private void onFinishLoading() {
        if (mCountRunningAsyn == 0) {
            mViewHome.showCategory(mCategories);
            mViewHome.showGenres(mGenres);
        }
    }

    @Override
    public void loadCategories() {
        getMovies(MovieType.POPULAR);
        getMovies(MovieType.TOP_RATE);
        getMovies(MovieType.UP_COMING);
        getMovies(MovieType.NOW_PLAYING);
    }

    @Override
    public void loadGenres() {
        mCountRunningAsyn++;
        mGenreRepository.getGenres(new Callback<ArrayList<Genre>>() {
            @Override
            public void onGetDataSuccess(ArrayList<Genre> data) {
                mGenres.addAll(data);
                onLoadDataSuccess();
            }

            @Override
            public void onGetDataFailure(Exception e) {
                onLoadDataFailure(e);
            }
        });
    }
}
