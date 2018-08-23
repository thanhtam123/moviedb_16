package com.example.admin.moviedbapplication.screen.home;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieDatabase;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.genre.GenresActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.CategoryAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.GenresAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.OnGenreItemClickListener;
import com.example.admin.moviedbapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/7/2018.
 */

public class HomeFragment extends Fragment implements
        HomeContract.View, OnGenreItemClickListener, CategoryAdapter.OnItemClickListener {

    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerCategory, mRecyclerGenre;
    private ProgressDialog mProgressDialog;
    private CategoryAdapter mAdapter;
    private MovieRepository mMovieRepository;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getinstance(MovieDatabase.getInstance(getContext()).movieDao()));
        mPresenter = new HomePresenter(this, mMovieRepository);
        mRecyclerCategory = view.findViewById(R.id.recycler_categery);
        mRecyclerGenre = view.findViewById(R.id.recycler_genre);
        mPresenter.loadGenres();
        mPresenter.loadCategories();
        Utils.initProgressDialog(getActivity(), mProgressDialog);
    }

    @Override
    public void showCategory(List<Category> categories){
        mAdapter = new CategoryAdapter(getActivity());
        mAdapter.setListener(this);
        mRecyclerCategory.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false));
        mRecyclerCategory.setAdapter(mAdapter);
        mAdapter.updateData(new ArrayList<>(categories));
    }

    @Override
    public void showLoadDataMainFail(Exception e) {
        Utils.dismissProgressDialog(mProgressDialog);
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGenres(List<Genre> genres) {
        GenresAdapter adapter = new GenresAdapter(getActivity());
        adapter.setOnItemClickedListener(this);
        mRecyclerGenre.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
        mRecyclerGenre.setAdapter(adapter);
        adapter.updateData(new ArrayList<>(genres));

    }

    @Override
    public void onGenreClick(Genre genre) {
        Intent intent = GenresActivity.getGenreIntent(getContext(), genre);
        startActivity(intent);
    }

    @Override
    public void onMovieClick(Movie movie) {
        startActivity(DetailActivity.getMovieIntent(getContext(), movie));
    }

    @Override
    public void onCategoryClick(Category category) {
        startActivity(GenresActivity.getCategoryIntent(getContext(), category));
    }
}
