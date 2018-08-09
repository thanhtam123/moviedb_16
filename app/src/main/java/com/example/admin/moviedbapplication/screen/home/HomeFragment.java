package com.example.admin.moviedbapplication.screen.home;

import android.app.ProgressDialog;
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

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.screen.home.adapter.CategoryAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.GenresAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.OnGenreItemClickListener;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class HomeFragment extends Fragment implements
        HomeContract.View, OnGenreItemClickListener {

    private HomePresenter mPresenter;
    private RecyclerView recyclerCategory,recyclerGenre;
    private ProgressDialog progressDialog;
    private CategoryAdapter adapter;
    private int page;

    public HomeFragment() {
        mPresenter = new HomePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
        progressDialog.dismiss();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void showLoadDataMainFail(Exception e) {
        progressDialog.dismiss();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
    }

    @Override
    public void showBanner(ArrayList<Movie> movies) {

    }

    @Override
    public void showLoadingAnimation() {
        progressDialog = new ProgressDialog(getActivity(),R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.text_loading));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        mPresenter.loadBanners();
        mPresenter.loadGenres();
        mPresenter.loadCategories(1);
        showLoadingAnimation();
    }

    @Override
    public void onGenreClick(Genre genre) {

    }
}