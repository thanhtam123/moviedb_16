package com.example.admin.moviedbapplication.screen.actor_detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;
import com.example.admin.moviedbapplication.utils.Constants;
import com.example.admin.moviedbapplication.utils.Utils;

import java.util.ArrayList;

public class DetailActorActivity extends AppCompatActivity implements DetailActorContract.View, OnItemMovieClickedListener {

    private DetailActorContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private Cast mCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        mCast = getIntent().getParcelableExtra(Constants.EXTRA_ACTOR);
        Log.e("TAG", mCast.toString());
        mPresenter = new DetailActorPresenter(this);
        mPresenter.loadMovies(mCast.getCreditId());
        Utils.dismissProgressDialog(mProgressDialog);
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error_load_image);

        ImageView background = findViewById(R.id.background);
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(Constants.IMAGE_PATH + movies.get(0).getBackdropPath())
                .into(background);

        TextView textNameActor = findViewById(R.id.text_name_actor_detail);
        textNameActor.setText(mCast.getName());
        ImageView imageActor = findViewById(R.id.image_profile_actor_detail);

        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(Constants.IMAGE_PATH + mCast.getProfilePath())
                .into(imageActor);

        initRecycleview(movies);
        Utils.dismissProgressDialog(mProgressDialog);
    }

    public void initRecycleview(ArrayList<Movie> movies){
        RecyclerView mRecyclerMovies = findViewById(R.id.recycler_actor_movie);
        mRecyclerMovies.setHasFixedSize(true);

        mRecyclerMovies.setLayoutManager(
                new LinearLayoutManager(this));
        mRecyclerMovies.setItemAnimator(new DefaultItemAnimator());
        ActorMovieAdapter mAdapter = new ActorMovieAdapter(this,  movies);
        mRecyclerMovies.setAdapter(mAdapter);
        mAdapter.setOnItemClickedListener(this);
    }

    @Override
    public void showMoviesFail(Exception e) {
        Utils.dismissProgressDialog(mProgressDialog);
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE,movie);
        startActivity(intent);
    }
}
