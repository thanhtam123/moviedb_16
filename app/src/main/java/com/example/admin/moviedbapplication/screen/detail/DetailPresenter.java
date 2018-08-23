package com.example.admin.moviedbapplication.screen.detail;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.MovieRepository;
import com.example.admin.moviedbapplication.data.source.remote.video.VideoRemoteDataSource;
import com.example.admin.moviedbapplication.data.source.remote.video.VideoRepository;

import java.util.List;

/**
 * Created by TamTT on 8/11/2018.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private VideoRepository mVideoRepository;
    private MovieRepository mMovieRepository;
    private DetailContract.View mViewDetail;

    public DetailPresenter(DetailContract.View viewDetail, MovieRepository movieRepository) {
        mViewDetail = viewDetail;
        mVideoRepository = VideoRepository.getInstance(VideoRemoteDataSource.getInstance());
        mMovieRepository = movieRepository;
    }

    @Override
    public void loadVideo(String id) {
        mVideoRepository.getVideo(id, new Callback<Video>() {
            @Override
            public void onGetDataSuccess(Video data) {
                mViewDetail.playVideo(data);
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mViewDetail.showVideoLoadFail(e.toString());
            }
        });
    }

    @Override
    public void addFavorites(Movie movie) {
        if (mMovieRepository.insertMovies(movie) > 0) {
            mViewDetail.updateFavoritesButton(true);
        } else {
            mViewDetail.updateFavoritesListFailure();
        }
    }

    @Override
    public void removeFavorites(String id) {
        if (mMovieRepository.deleteMovie(id) > 0) {
            mViewDetail.updateFavoritesButton(false);
        } else {
            mViewDetail.updateFavoritesListFailure();
        }
    }

    @Override
    public void loadListFavorites() {
        mMovieRepository.getAllMovies(new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                mViewDetail.getFavorites(data);
            }

            @Override
            public void onGetDataFailure(Exception e) {

            }
        });
    }

    @Override
    public boolean checkFavorite(String idMovie) {
        if (mMovieRepository.isFavorites(idMovie) == null) {
            return false;
        }
        return true;
    }
}
