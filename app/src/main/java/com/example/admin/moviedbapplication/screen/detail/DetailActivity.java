package com.example.admin.moviedbapplication.screen.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.actor.ActorActivity;
import com.example.admin.moviedbapplication.utils.Constants;
import com.example.admin.moviedbapplication.utils.DataGenreClass;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity
        implements DetailContract.View, YouTubePlayer.OnInitializedListener,
        View.OnClickListener {

    private DetailContract.Presenter mDetailPresenter;
    private Movie mMovie;
    private YouTubePlayer mYouTubePlayer;
    private boolean mWasRestored;

    private TextView mTextMovieName;
    private TextView mTextGenreName;
    private TextView mTextTime;
    private TextView mTextOverview;
    private RatingBar mRatingBar;
    private Button mButtonLike;
    private Button mButtonCast;
    private ImageView mImageMovie;
    private ImageView mImageMovieBackdrop;
    private List<Genre> mGenres;
    private ArrayList<String> mListId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        mMovie = getIntent().getParcelableExtra(Constants.EXTRA_MOVIE);

        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(DetailActivity.this));
        mDetailPresenter = new DetailPresenter(this, movieRepository);
        mGenres = DataGenreClass.getListGenres(mMovie.getGenreIds());
        Log.e("TAG", mListId.toString());
        mListId = mDetailPresenter.getListFavoriteIdMovie();
        showMovie(mMovie);
    }

    @SuppressLint("CheckResult")
    @Override
    public void showMovie(Movie movie) {
        initUI();
        mTextMovieName.setText(movie.getTitle());
        mTextTime.setText(movie.getReleaseDate());
        mTextGenreName.setText(DataGenreClass.getGenreString(new ArrayList<>(mGenres)));
        mRatingBar.setRating((float) movie.getVoteAverage());
        mTextOverview.setText(movie.getOverview());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error_load_image);
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(Constants.IMAGE_PATH + movie.getPosterPath())
                .into(mImageMovie);
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(Constants.IMAGE_PATH + movie.getBackdropPath())
                .into(mImageMovieBackdrop);
        mButtonCast.setOnClickListener(this);
        mButtonLike.setOnClickListener(this);
    }

    public void initUI() {
        mTextMovieName = findViewById(R.id.text_name_movie_detail);
        mTextGenreName = findViewById(R.id.text_name_genres_detail);
        mTextTime = findViewById(R.id.text_time_detail);
        mTextOverview = findViewById(R.id.text_overview);
        mRatingBar = findViewById(R.id.rating_movie_detail);
        mButtonLike = findViewById(R.id.button_like);
        mButtonCast = findViewById(R.id.button_cast_detail);
        mImageMovie = findViewById(R.id.image_movie_detail);
        mImageMovieBackdrop = findViewById(R.id.backdrop);
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().
                        findFragmentById(R.id.youtube_player_view);
        frag.initialize(BuildConfig.YoutubeKey, this);
        if(mListId!= null && mListId.contains(mMovie.getId())){
            mButtonLike.setText(getString(R.string.liked));
        }else {
            mButtonLike.setText(getString(R.string.like));
        }
    }

    @Override
    public void showVideoLoadFail(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void playVideo(Video video) {
        try {
            mYouTubePlayer.setPlayerStateChangeListener(mPlayerStateChangeListener);
            mYouTubePlayer.setPlaybackEventListener(mPlaybackEventListener);
            if (!mWasRestored) {
                mYouTubePlayer.cueVideo(video.getKey());
            }
        }catch (IllegalStateException e){
            mYouTubePlayer.release();
        }

    }

    @Override
    public void getListIdFavorites(ArrayList<String> ids) {
        //mListId = ids;
    }

    private YouTubePlayer.PlaybackEventListener mPlaybackEventListener =
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
    private YouTubePlayer.PlayerStateChangeListener mPlayerStateChangeListener =
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
        showVideoLoadFail(youTubeInitializationResult.toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cast_detail:
                Intent intent = new Intent(DetailActivity.this, ActorActivity.class);
                intent.putExtra(Constants.EXTRA_MOVIE, mMovie);
                startActivity(intent);
                break;
            case R.id.button_like:
                if(mButtonLike.getText().toString().compareToIgnoreCase("LIKE") == 0){
                    mDetailPresenter.addMovieIntoFavoriesList(mMovie.getId());
                    mListId.add(mMovie.getId());
                    mButtonLike.setText(R.string.liked);
                }else {
                    mDetailPresenter.removeMovieIntoFavoriesList(mMovie.getId());
                    mListId.remove(mMovie.getId());
                    mButtonLike.setText(R.string.like);
                }
                break;
        }
    }
}
