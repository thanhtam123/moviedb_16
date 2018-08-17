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
        String url = API.BASE_URL + API.MOVIE + API.SLASH +
                idMovie + API.VIDEO + API.API_KEY + BuildConfig.ApiKey;
        new VideoRemoteAsyntask(callback).execute(url);
    }
}
