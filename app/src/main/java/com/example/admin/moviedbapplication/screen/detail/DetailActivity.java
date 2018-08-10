package com.example.admin.moviedbapplication.screen.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.utils.Constants;
import com.example.admin.moviedbapplication.utils.DataClass;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class DetailActivity extends AppCompatActivity
        implements DetailContract.View, YouTubePlayer.OnInitializedListener,
        View.OnClickListener {

    private DetailPresenter mDetailPresenter;
    private Movie mMovie;
    private YouTubePlayer mYouTubePlayer;
    private boolean mWasRestored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        mDetailPresenter = new DetailPresenter(this);
        mMovie = getIntent().getParcelableExtra(Constants.EXTRA_MOVIE);
        showMovie(mMovie);
    }

    @SuppressLint("CheckResult")
    @Override
    public void showMovie(Movie movie) {
        TextView textMovieName = findViewById(R.id.text_name_movie_detail);
        TextView textGenreName = findViewById(R.id.text_name_genres_detail);
        TextView textTime = findViewById(R.id.text_time_detail);
        TextView textOverview = findViewById(R.id.text_overview);
        RatingBar ratingBar = findViewById(R.id.rating_movie_detail);
        Button buttonLike = findViewById(R.id.button_like);
        Button buttonCast = findViewById(R.id.button_cast_detail);
        ImageView imageMovie = findViewById(R.id.image_movie_detail);
        ImageView imageMovieBackdrop = findViewById(R.id.backdrop);
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().
                        findFragmentById(R.id.youtube_player_view);
        frag.initialize(BuildConfig.YoutubeKey, this);

        textMovieName.setText(movie.getTitle());
        textTime.setText(movie.getReleaseDate());
        textGenreName.setText(DataClass.getData(movie.getGenreIds()[0], movie.getGenreIds()[1]));
        ratingBar.setRating((float) movie.getPopularity());
        textOverview.setText(movie.getOverview());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error_load_image);
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(Constants.IMAGE_PATH + movie.getPosterPath())
                .into(imageMovie);
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(Constants.IMAGE_PATH + movie.getBackdropPath())
                .into(imageMovieBackdrop);
        buttonCast.setOnClickListener(this);
        buttonLike.setOnClickListener(this);
    }

    @Override
    public void showVideoFail(String e) {
        Toast.makeText(this, getString(R.string.text_load_movie_fail),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void playVideo(Video video) {
        mYouTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        mYouTubePlayer.setPlaybackEventListener(playbackEventListener);
        if (!mWasRestored) {
            mYouTubePlayer.cueVideo(video.getKey());
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener =
            new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener =
            new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
        }

        @Override
        public void onVideoStarted() {
        }
    };

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        mWasRestored = b;
        mYouTubePlayer = youTubePlayer;
        mDetailPresenter.loadVideo(mMovie.getId());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        showVideoFail(youTubeInitializationResult.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cast_detail:
                break;
            case R.id.button_like:
                break;
        }
    }
}
