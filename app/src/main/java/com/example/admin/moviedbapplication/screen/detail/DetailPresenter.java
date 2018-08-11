package com.example.admin.moviedbapplication.screen.detail;

import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.video.VideoRemoteDataSource;
import com.example.admin.moviedbapplication.data.source.remote.video.VideoRepository;

/**
 * Created by TamTT on 8/11/2018.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private VideoRepository mVideoRepository;
    private DetailContract.View mViewDetail;
    public DetailPresenter(DetailContract.View viewDetail) {
        mViewDetail = viewDetail;
        mVideoRepository = VideoRepository.getInstance(VideoRemoteDataSource.getInstance());
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
}
