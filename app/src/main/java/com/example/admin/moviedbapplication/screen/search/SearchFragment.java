package com.example.admin.moviedbapplication.screen.search;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.moviedbapplication.OnLoadMoreListener;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.home.HomeActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by TamTT on 8/9/2018.
 */

public class SearchFragment extends Fragment implements
        SearchContract.View, OnItemMovieClickedListener, OnLoadMoreListener {

    private SearchView mSearchView = null;
    private MovieSearchAdapter mMovieSearchAdapter;
    private RecyclerView mRecyclerSearch;
    private SearchPresenter mSearchPresenter;
    private SearchView.OnQueryTextListener mQueryTextListener;
    private int mPage = 1;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private String mName = "";


    public SearchFragment() {
        mSearchPresenter = new SearchPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        if (((HomeActivity) getActivity()) != null) {
            ((HomeActivity) getActivity()).setTitle(getResources().getString(R.string.nav_search));
        }
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
                int waitingTime = 400;
                CountDownTimer cntr = null;

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (cntr != null) {
                        cntr.cancel();
                    }
                    mName = mSearchView.getQuery().toString().trim();

                    if(!Objects.equals(mName, "") && mName.toCharArray().length >= 3){
                        cntr.start();
                    }
                    cntr = new CountDownTimer(waitingTime, 500) {

                        public void onTick(long millisUntilFinished) {
                        }
                        public void onFinish() {
                            mPage = 1;
                            mSearchPresenter.loadSearchMovie(mPage, mName);
                        }
                    };

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
    public void showListPopularMovie(ArrayList<Movie> movies) {
        mMovieSearchAdapter.updateData(movies, mPage);
    }

    @Override
    public void updateListSearch(ArrayList<Movie> movies, int page) {
        if (movies.size() == 0) {
            mMovieSearchAdapter.notifyItemRemoved(mMovies.size());
            return;
        }
        if (page == 1) {
            mMovies.clear();
            mMovies.addAll(movies);
            mRecyclerSearch.setLayoutManager(
                    new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
            mMovieSearchAdapter = new MovieSearchAdapter(getActivity(), mRecyclerSearch, movies);
            mMovieSearchAdapter.setOnItemClickedListener(this);
            mRecyclerSearch.setAdapter(mMovieSearchAdapter);
            mMovieSearchAdapter.setOnLoadMoreListener(this);

        } else {
            mMovies.addAll(movies);
            mMovieSearchAdapter.updateData(movies, mPage);
        }
        mMovieSearchAdapter.setLoaded();
    }

    @Override
    public void showListMovieLoadFail(Exception e) {
        Toast.makeText(getActivity(), getString(R.string.msg_loading_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnimationLoading() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE, movie);
        getActivity().startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        if (mMovies.size() >= 1000) {
            Toast.makeText(getActivity(), getString(R.string.msg_end_list), Toast.LENGTH_LONG).show();
            mMovieSearchAdapter.notifyItemRemoved(mMovies.size());
        } else {
            mMovieSearchAdapter.notifyItemInserted(mMovies.size() - 1);
            mMovieSearchAdapter.notifyItemRemoved(mMovies.size());
            mPage += 1;
            mSearchPresenter.loadSearchMovie(mPage, mName);
        }
    }
}
