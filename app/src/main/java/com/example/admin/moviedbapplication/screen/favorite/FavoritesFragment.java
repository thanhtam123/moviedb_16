package com.example.admin.moviedbapplication.screen.favorite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.home.HomeActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/13/2018.
 */

public class FavoritesFragment extends Fragment implements FavoritesContract.View,
        OnItemMovieClickedListener {

    private FavoritesPresenter mFavoritesPresenter;
    private ArrayList<Movie> mMovieArrayList;
    private RecyclerView mRecyclerFavorites;
    private FavoritesAdapter mAdapter;
    private ArrayList<String> mArrayId;
    private ProgressDialog mProgressDialog;
    private MovieRepository mMovieRepository;

    public FavoritesFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_favorites, container, false);
        if (((HomeActivity) getActivity()) != null) {
            ((HomeActivity) getActivity()).setTitle(getResources().getString(R.string.nav_favorites));
        }

        mArrayId = mFavoritesPresenter.loadFavoriteMovieIdInLocal();
        mFavoritesPresenter.loadMovies(mArrayId);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMovieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(getContext()));
        mFavoritesPresenter = new FavoritesPresenter(this, mMovieRepository);
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE,movie);
        startActivity(intent);
    }


    @Override
    public void showListMovie(ArrayList<Movie> arrayList) {
        dimissAnimationLoading();

        mMovieArrayList = new ArrayList<>();
        mMovieArrayList.addAll(arrayList);
        mRecyclerFavorites = getView().findViewById(R.id.recycler_favorite);
        mRecyclerFavorites.setHasFixedSize(true);

        mRecyclerFavorites.setLayoutManager(
                new LinearLayoutManager(getContext()));
        mAdapter = new FavoritesAdapter(getContext(), arrayList, mFavoritesPresenter);
        mRecyclerFavorites.setAdapter(mAdapter);
        mAdapter.setOnItemClickedListener(this);
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    public void showListMovieFail(Exception e) {
        Toast.makeText(getContext(), "Loading fail "+e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnimationLoading() {
        mProgressDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.text_loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void dimissAnimationLoading() {

    }

}
