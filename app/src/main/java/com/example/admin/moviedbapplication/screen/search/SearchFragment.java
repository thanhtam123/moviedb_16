package com.example.admin.moviedbapplication.screen.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/9/2018.
 */

public class SearchFragment extends Fragment implements
        SearchContract.View, OnItemMovieClickedListener {

    private SearchView mSearchView = null;
    private MovieSearchAdapter mMovieSearchAdapter;
    private RecyclerView mRecyclerSearch;
    private SearchPresenter mSearchPresenter;
    private SearchView.OnQueryTextListener mQueryTextListener;
    private int mPage = 1;

    public SearchFragment() {
        mSearchPresenter = new SearchPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerSearch = view.findViewById(R.id.recycler_search_movie);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().
                getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            mSearchView = (SearchView) searchItem.getActionView();
        }
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.
                    getSearchableInfo(getActivity().getComponentName()));

            mQueryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    mSearchPresenter.loadSearchMovie(mPage, newText);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            mSearchView.setOnQueryTextListener(mQueryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchPresenter.loadRecommendMovies(MovieType.POPULAR, mPage);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return false;
            default:
                break;
        }
        mSearchView.setOnQueryTextListener(mQueryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showListPopularMovie(ArrayList<Movie> movies) {
        mMovieSearchAdapter = new MovieSearchAdapter(getActivity());
        mMovieSearchAdapter.updateData(movies);
        mRecyclerSearch.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false));
        mMovieSearchAdapter.setOnItemClickedListener(this);
        mRecyclerSearch.setAdapter(mMovieSearchAdapter);
    }

    @Override
    public void updateListSearch(ArrayList<Movie> movies) {
        mMovieSearchAdapter.updateData(movies);
    }

    @Override
    public void showListMovieLoadFail(Exception e) {

    }

    @Override
    public void showAnimationLoading() {
    }

    @Override
    public void onMovieClick(Movie movie) {
    }
}
