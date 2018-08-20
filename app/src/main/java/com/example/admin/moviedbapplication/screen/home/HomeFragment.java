package com.example.admin.moviedbapplication.screen.home;

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
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;
import com.example.admin.moviedbapplication.data.source.local.MovieLocalDataSource;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRemoteDataSource;
import com.example.admin.moviedbapplication.screen.genre.GenresActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.CategoryAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.GenresAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.OnGenreItemClickListener;
import com.example.admin.moviedbapplication.utils.Constants;
import com.example.admin.moviedbapplication.utils.Utils;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class HomeFragment extends Fragment implements
        HomeContract.View, OnGenreItemClickListener {

    private static HomeFragment mHomeFragment;
    private HomePresenter mPresenter;
    private RecyclerView recyclerCategory, recyclerGenre;
    private ProgressDialog progressDialog;
    private CategoryAdapter adapter;
    private ArrayList<Category> mCategories;
    private ArrayList<Genre> mGenres;
    private ProgressDialog mProgressDialog;
    private static boolean mIsFirstLoad;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            mIsFirstLoad = true;
        }else {
            mIsFirstLoad = false;
        }
        args.putBoolean(Constants.EXTRA_FIRST_LOAD, mIsFirstLoad);
        mHomeFragment.setArguments(args);
        return mHomeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!mIsFirstLoad){
            showCategory(mCategories);
            showGenres(mGenres);
        }else {
            mPresenter.loadGenres();
            mPresenter.loadCategories(1);
            Utils.initProgressDialog(getActivity(), mProgressDialog);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance(getContext()));
        mPresenter = new HomePresenter(this, movieRepository);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (((HomeActivity) getActivity()) != null) {
            ((HomeActivity) getActivity()).setTitle(getResources().getString(R.string.home));
        }
        recyclerCategory = view.findViewById(R.id.recycler_categery);
        recyclerGenre = view.findViewById(R.id.recycler_genre);
        return view;
    }

    @Override
    public void showCategory(ArrayList<Category> categories) {
        adapter = new CategoryAdapter(getActivity());
        recyclerCategory.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false));
        recyclerCategory.setAdapter(adapter);
        adapter.updateData(categories);
        Utils.dismissProgressDialog(mProgressDialog);
        mCategories = categories;
    }

    @Override
    public void showLoadDataMainFail(Exception e) {
        Utils.dismissProgressDialog(mProgressDialog);
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGenres(ArrayList<Genre> genres) {
        GenresAdapter adapter = new GenresAdapter(getActivity());
        adapter.setOnItemClickedListener(this);
        recyclerGenre.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
        recyclerGenre.setAdapter(adapter);
        adapter.updateData(genres);
        mGenres = genres;
    }

    @Override
    public void showBanner(ArrayList<Movie> movies) {

    }

    @Override
    public void showLoadingAnimation() {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.text_loading));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void onGenreClick(Genre genre) {
        Intent intent = new Intent(getActivity(), GenresActivity.class);
        intent.putExtra(Constants.EXTRA_GENRE, genre);
        getActivity().startActivity(intent);
    }
}
