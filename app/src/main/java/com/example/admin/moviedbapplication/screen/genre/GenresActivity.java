package com.example.admin.moviedbapplication.screen.genre;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieDatabase;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.EndlessRecyclerViewScrollListener;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;

public class GenresActivity extends AppCompatActivity implements GenreContract.View,
        OnItemMovieClickedListener {

    private ArrayList<Movie> mMovieArrayList;
    private MovieGenreAdapter mAdapter;
    private GenreContract.Presenter mGenrePresenter;
    private RequestOptions mRequestOptions;
    private boolean mIsLoading;
    private MovieRepository mMovieRepository;
    private int mPage = 1;
    private ProgressDialog mProgressDialog;
    private Category mCategory;
    private Genre mGenre;

    public static Intent getCategoryIntent(Context context, Category category) {
        Intent intent = new Intent(context, GenresActivity.class);
        intent.putExtra(Constants.EXTRA_CATEGORY, category);
        return intent;
    }

    public static Intent getGenreIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, GenresActivity.class);
        intent.putExtra(Constants.EXTRA_GENRE, genre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);

        mMovieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getinstance(MovieDatabase.getInstance(this).movieDao()));
        mGenrePresenter = new GenrePresenter(this, mMovieRepository);
        mGenre = getIntent().getParcelableExtra(Constants.EXTRA_GENRE);
        mCategory = getIntent().getParcelableExtra(Constants.EXTRA_TYPE);

        initRecyclerView();

        if (mGenre != null) {
            initCollapsingToolbar(mGenre.getGenreName());
            mGenrePresenter.loadGenres(mGenre, mPage);
            mPage += 1;
            showAnimationLoading();
        } else {
            initCollapsingToolbar(mCategory.getCategeryName());
            showGenres(mCategory.getCategoryMovie());
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void showGenres(ArrayList<Movie> movies) {
        mMovieArrayList.addAll(movies);
        mAdapter.removeLoadingIndicator();
        if (mPage <= Constants.SECOND_PAGE) {
            mAdapter.notifyDataSetChanged();
            ImageView imageBackdrop = findViewById(R.id.image_backdrop_genre);
            Glide.with(this)
                    .setDefaultRequestOptions(mRequestOptions)
                    .load(Constants.IMAGE_PATH + mMovieArrayList.get(0).getPosterPath())
                    .into(imageBackdrop);
        } else {
            mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(),
                    mMovieArrayList.size() - 1);
        }
        mIsLoading = false;
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    public void showGenresFail(Exception e) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnimationLoading() {
        mProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.text_loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @SuppressLint("CheckResult")
    @Override
    public void initRecyclerView() {
        mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error_load_image);

        mMovieArrayList = new ArrayList<>();
        mAdapter = new MovieGenreAdapter(this, mMovieArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView mRecyclerGenre = findViewById(R.id.recycler_genre);
        mRecyclerGenre.setLayoutManager(linearLayoutManager);
        mRecyclerGenre.setAdapter(mAdapter);
        mAdapter.setOnItemClickedListener(this);
        mRecyclerGenre.addOnScrollListener(
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        if (mIsLoading) {
                            return;
                        }
                        mPage ++;
                        loadData();
                    }
                });
    }

    private void loadData() {
        mIsLoading = true;
        mAdapter.addLoadingIndicator();
        if (mGenre != null) {
            mGenrePresenter.loadGenres(mGenre, mPage);
        } else {
            mGenrePresenter.loadCategories(mPage, mCategory.getCategeryName().
                    toLowerCase().replace(' ', '_'));
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = DetailActivity.getMovieIntent(this, movie);
        startActivity(intent);
    }

    void initCollapsingToolbar(String title) {
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(Html.fromHtml("<font color=#1a0042>"
                + title + "</font>"));
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
}
