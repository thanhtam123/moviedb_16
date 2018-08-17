package com.example.admin.moviedbapplication.data.source.remote.video;

import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.API;

/**
 * Created by TamTT on 8/11/2018.
 */

public class VideoRemoteDataSource implements VideoDataSource {
    private static VideoRemoteDataSource sInstance;

    private VideoRemoteDataSource() {
    }

    public static VideoRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new VideoRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getVideo(String idMovie, Callback<Video> callback) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API.BASE_URL)
                .append(API.DISCOVER)
                .append(API.MOVIE)
                .append(API.SLASH)
                .append(idMovie)
                .append(API.VIDEO)
                .append(API.API_KEY)
                .append(BuildConfig.ApiKey);
        String url = stringBuilder.toString();
        new VideoRemoteAsyntask(callback).execute(url);
    }
}
