package com.example.admin.moviedbapplication.screen.home;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.GenreRepository;
import com.example.admin.moviedbapplication.data.source.MovieRepository;
import com.example.admin.moviedbapplication.data.source.remote.GenreRemoteDataSource;
import com.example.admin.moviedbapplication.data.source.remote.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private MovieRepository mMovieRepository;
    private GenreRepository mGenreRepository;
    private HomeContract.View mViewHome;
    private Category mCategoryPopular, mCategoryToprate, mCategoryUpcoming, mCategoryNowPlaying;
    private ArrayList<Genre> mGenres;

    public HomePresenter(HomeContract.View viewHome) {
        mViewHome = viewHome;
        mMovieRepository = MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
        mGenreRepository = GenreRepository.getInstance(GenreRemoteDataSource.getInstance());
    }

    @Override
    public void loadCategories(int page) {
        mMovieRepository.getMovies(MovieType.POPULAR, page, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mCategoryPopular =  data;
                mOnListenLoadingComplete.loadDone(Constants.POPULAR, data, null);
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mOnListenLoadingComplete.loadFail(e);
            }
        });

        mMovieRepository.getMovies(MovieType.TOP_RATE, page, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mCategoryToprate = data;
                mOnListenLoadingComplete.loadDone(Constants.TOP_RATE, data, null);
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mOnListenLoadingComplete.loadFail(e);
            }
        });

        mMovieRepository.getMovies(MovieType.UP_COMING, page, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mCategoryUpcoming = data;
                mOnListenLoadingComplete.loadDone(Constants.UPCOMING, data, null);
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mOnListenLoadingComplete.loadFail(e);
            }
        });

        mMovieRepository.getMovies(MovieType.NOW_PLAYING, page, new Callback<Category>() {
            @Override
            public void onGetDataSuccess(Category data) {
                mCategoryNowPlaying = data;
                mOnListenLoadingComplete.loadDone(Constants.NOW_PLAYING, data, null);
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mOnListenLoadingComplete.loadFail(e);
            }
        });
    }

    private OnListenLoadingComplete mOnListenLoadingComplete = new OnListenLoadingComplete() {
        boolean popular = false;
        boolean toprate = false;
        boolean upcoming = false;
        boolean nowplaying = false;
        boolean genre = false;
        @Override
        public void loadDone(String asyncClassName, Category category, ArrayList<Genre> genres) {
            if(asyncClassName.equals(Constants.POPULAR)) {
                popular = true;
            } else if(asyncClassName.equals(Constants.TOP_RATE)) {
                toprate = true;
            }else if(asyncClassName.equals(Constants.UPCOMING)) {
                upcoming = true;
            }else if(asyncClassName.equals(Constants.NOW_PLAYING)) {
                nowplaying = true;
            }else if(asyncClassName.equals(Constants.GENRES)){
                genre = true;
            }
            if(popular && upcoming && toprate && nowplaying && genre) {
                ArrayList<Category> categories = new ArrayList<>();
                categories.add(mCategoryToprate);
                categories.add(mCategoryUpcoming);
                categories.add(mCategoryPopular);
                categories.add(mCategoryNowPlaying);
                mViewHome.showCategory(categories);
                mViewHome.showGenres(mGenres);
            }
        }

        @Override
        public void loadFail(Exception e) {
            mViewHome.showLoadDataMainFail(e);
        }
    };

    @Override
    public void loadBanners() {

    }

    @Override
    public void loadGenres() {
        mGenreRepository.getGenres(new Callback<ArrayList<Genre>>() {
            @Override
            public void onGetDataSuccess(ArrayList<Genre> data) {
                mGenres = data;
                mOnListenLoadingComplete.loadDone(Constants.GENRES, null,data);
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mOnListenLoadingComplete.loadFail(e);
            }
        });
    }

    @Override
    public void openMovieDetails(@NonNull Movie movie) {

    }
}
