package com.example.admin.moviedbapplication.screen.detail;

import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.SharedPrefsKey;
import com.example.admin.moviedbapplication.data.source.remote.video.VideoRemoteDataSource;
import com.example.admin.moviedbapplication.data.source.remote.video.VideoRepository;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/11/2018.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private VideoRepository mVideoRepository;
    private DetailContract.View mViewDetail;
    private MovieRepository mMovieRepository;

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
    public void addMovieIntoFavoriesList(String id) {
        mMovieRepository.insertMovie(SharedPrefsKey.FAVORITES_LIST_KEY, id);
    }

    @Override
    public void removeMovieIntoFavoriesList(String id) {
        mMovieRepository.removeMovie(SharedPrefsKey.FAVORITES_LIST_KEY, id);
    }

    @Override
    public ArrayList<String> getListFavoriteIdMovie() {
        return mMovieRepository.getListIdMovie(SharedPrefsKey.FAVORITES_LIST_KEY);
    }
}
