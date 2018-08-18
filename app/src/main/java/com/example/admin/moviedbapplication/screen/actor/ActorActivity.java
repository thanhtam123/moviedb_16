package com.example.admin.moviedbapplication.screen.actor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.utils.Constants;
import com.example.admin.moviedbapplication.utils.Utils;

import java.util.ArrayList;

public class ActorActivity extends AppCompatActivity
        implements ActorContract.View, OnActorClickListener {

    private ProgressDialog mProgressDialog;
    private ActorContract.Presenter mActorPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);
        Movie movie = getIntent().getParcelableExtra(Constants.EXTRA_MOVIE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarReadAct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#757575'>"
                +movie.getTitle()+"</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);

        mActorPresenter = new ActorPresenter(this);

        mActorPresenter.loadCasts(movie.getId());

        Utils.initProgressDialog(this, mProgressDialog);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showListCasts(ArrayList<Cast> casts) {
        RecyclerView recyclerActors = findViewById(R.id.recycler_actor);

        RecyclerView.LayoutManager layoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerActors.setLayoutManager(layoutManager);

        ActorAdapter adapter = new ActorAdapter(this, casts);
        recyclerActors.setAdapter(adapter);

        adapter.setOnActorClickListener(this);
        Utils.dismissProgressDialog(mProgressDialog);
    }

    @Override
    public void showListCastsFail(Exception e) {
        Utils.dismissProgressDialog(mProgressDialog);
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActorClick(Cast cast) {
        /*Intent intent = new Intent(ActorActivity.this, DetailActorActivity.class);
        intent.putExtra(Constants.EXTRA_ACTOR, cast);
        startActivity(intent);*/
    }
}
