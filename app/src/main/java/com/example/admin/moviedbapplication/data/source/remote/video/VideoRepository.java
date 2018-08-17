package com.example.admin.moviedbapplication.data.source.remote.video;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;

/**
 * Created by TamTT on 8/10/2018.
 */

public class VideoRepository implements VideoDataSource {

    private static VideoRepository sInstance;
    @NonNull
    private VideoDataSource mVideoDataSource;

    private VideoRepository() {
    }

    private VideoRepository(@NonNull VideoDataSource videoDataSource) {
        mVideoDataSource = videoDataSource;
    }

    public static VideoRepository getInstance(@NonNull VideoDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new VideoRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getVideo(String idMovie, Callback<Video> callback) {
        mVideoDataSource.getVideo(idMovie,callback);
    }
}
