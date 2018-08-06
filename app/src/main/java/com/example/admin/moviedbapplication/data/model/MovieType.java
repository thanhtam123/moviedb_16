package com.example.admin.moviedbapplication.data.model;

import android.support.annotation.StringDef;

import static com.example.admin.moviedbapplication.data.model.MovieType.NOW_PLAYING;
import static com.example.admin.moviedbapplication.data.model.MovieType.POPULAR;
import static com.example.admin.moviedbapplication.data.model.MovieType.TOP_RATE;
import static com.example.admin.moviedbapplication.data.model.MovieType.UP_COMING;

/**
 * Created by TamTT on 8/6/2018.
 */

@StringDef({TOP_RATE, POPULAR, NOW_PLAYING, UP_COMING})
public @interface MovieType {
    String TOP_RATE = "top_rated";
    String POPULAR = "popular";
    String NOW_PLAYING = "now_playing";
    String UP_COMING  = "up_coming";
}
