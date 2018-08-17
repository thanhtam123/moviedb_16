package com.example.admin.moviedbapplication.data.source.remote.video;

import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;

/**
 * Created by TamTT on 8/10/2018.
 */

public interface VideoDataSource {
    void getVideo(String idMovie, Callback<Video> callback);
}
