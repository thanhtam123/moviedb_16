package com.example.admin.moviedbapplication.screen.search;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.Toast;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.screen.EndlessRecyclerViewScrollListener;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/9/2018.
 */

public class SearchFragment extends Fragment implements
        SearchContract.View, OnItemMovieClickedListener {
    public static final int FIRST_PAGE = 1;
    private static final int WAITING_TIME = 500;
    private static final char EMPTY_STRING = ' ';
    private static final char REPLACE_STRING = '+';

    private SearchView mSearchView = null;
    private MovieSearchAdapter mAdapter;
    private SearchPresenter mSearchPresenter;
    private SearchView.OnQueryTextListener mQueryTextListener;
    private int mPage = FIRST_PAGE;
    private boolean mIsLoading;
    private String mSearchKey;

    private CountDownTimer mCountDownTimer = new CountDownTimer(WAITING_TIME, WAITING_TIME) {

        public void onTick(long millisUntilFinished) {
            // no ops
        }

        public void onFinish() {
            loadData();
        }
    };

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchPresenter = new SearchPresenter(this);
        initRecyclerView();
    }

    private void loadData() {
        if (mIsLoading) {
            return;
        }
        mPage = FIRST_PAGE;
        mSearchPresenter.loadSearchMovie(mPage, mSearchKey);
        mIsLoading = true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, final MenuInflater inflater) {
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
                    if (mCountDownTimer != null) {
                        mCountDownTimer.cancel();
                    }
                    mSearchKey = newText.trim();
                    mSearchKey = mSearchKey.replace(EMPTY_STRING, REPLACE_STRING);
                    if (!mSearchKey.isEmpty() && mSearchKey.length() >= 0) {
                        mCountDownTimer.start();
                    }
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
    public void onGetMovieSuccess(ArrayList<Movie> movies) {
        mAdapter.removeLoadingIndicator();
        mAdapter.addData(movies);
        mIsLoading = false;
    }

    @Override
    public void onGetMoviesFailure(Exception e) {
        mAdapter.removeLoadingIndicator();
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoResult() {
        Toast.makeText(getActivity(), R.string.msg_no_movie_match, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearData() {
        mAdapter.clearData();
    }

    @Override
    public void addLoadingIndicator() {
        mAdapter.addLoadingIndicator();
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = DetailActivity.getMovieIntent(getActivity(), movie);
        startActivity(intent);
    }

    @SuppressLint("CheckResult")
    public void initRecyclerView() {
        mAdapter = new MovieSearchAdapter(getContext());
        mAdapter.setOnItemClickedListener(this);

        RecyclerView recyclerSearch = getView().findViewById(R.id.recycler_search_movie);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerSearch.setLayoutManager(linearLayoutManager);
        recyclerSearch.setAdapter(mAdapter);
        recyclerSearch.addOnScrollListener(
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                       if (!mIsLoading){
                          mPage++;
                          loadData();
                       }
                    }
                });
    }

}
