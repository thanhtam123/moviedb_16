package com.example.admin.moviedbapplication.screen.detail;

import android.os.Bundle;

import com.example.admin.moviedbapplication.R;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class DetailActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
    }
}
