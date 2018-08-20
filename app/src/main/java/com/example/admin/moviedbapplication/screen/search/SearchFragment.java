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

import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.MovieType;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.EndlessRecyclerViewScrollListener;
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
        SearchContract.View, OnItemMovieClickedListener {

    private SearchView mSearchView = null;
    private MovieSearchAdapter mAdapter;
    private SearchPresenter mSearchPresenter;
    private SearchView.OnQueryTextListener mQueryTextListener;
    private int mPage = 1;
    private ArrayList<Movie> mMovieArrayList;
    private Boolean mIsLoading = false;
    private String mName;
    private View view;

    public SearchFragment() {
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(getActivity()));
        mSearchPresenter = new SearchPresenter(this, movieRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        if (((HomeActivity) getActivity()) != null) {
            ((HomeActivity) getActivity()).setTitle(getResources().getString(R.string.nav_search));
        }
        initRecyclerView();
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
                int waitingTime = 1000;
                CountDownTimer mCountDownTimer = null;

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (mCountDownTimer != null) {
                        mCountDownTimer.cancel();
                    }
                    mName = mSearchView.getQuery().toString().trim();
                    mName = mName.replace(' ', '+');
                    if (!Objects.equals(mName, "") && mName.toCharArray().length >= 3) {
                        mCountDownTimer.start();
                    }
                    mCountDownTimer = new CountDownTimer(waitingTime, 500) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            if (!mIsLoading) {
                                mPage = 1;
                                mSearchPresenter.loadSearchMovie(mPage, mName);
                                mIsLoading = true;
                            }
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
    }

    @Override
    public void updateListSearch(ArrayList<Movie> movies) {
        if (movies.size() == 0) {
            Toast.makeText(getActivity(),
                    getString(R.string.msg_no_movie_match),
                    Toast.LENGTH_SHORT).show();
            mIsLoading = false;
            return;
        }
        mAdapter.removeLoadingIndicator();
        if (mPage <= 1) {
            mMovieArrayList.clear();
            mMovieArrayList.addAll(movies);
            mAdapter.notifyDataSetChanged();
        } else {
            mMovieArrayList.addAll(movies);
            mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(),
                    mMovieArrayList.size() - 1);
        }
        mIsLoading = false;
    }

    @Override
    public void showListMovieLoadFail(Exception e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnimationLoading() {
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @SuppressLint("CheckResult")
    public void initRecyclerView() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error_load_image);
        mMovieArrayList = new ArrayList<>();
        mAdapter = new MovieSearchAdapter(getActivity(), mMovieArrayList);
        RecyclerView recyclerSearch = view.findViewById(R.id.recycler_search_movie);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerSearch.setLayoutManager(linearLayoutManager);
        recyclerSearch.setAdapter(mAdapter);
        mAdapter.setOnItemClickedListener(this);
        recyclerSearch.addOnScrollListener(
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        if (!mIsLoading) {
                            mPage += 1;
                            mAdapter.addLoadingIndicator();
                            mSearchPresenter.loadSearchMovie(mPage, mName);
                            mIsLoading = true;
                        }
                    }
                });
    }
}