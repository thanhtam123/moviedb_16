package com.example.admin.moviedbapplication.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.model.CategoryMovie;
import com.example.admin.moviedbapplication.model.Genres;

import java.util.ArrayList;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * Created by TamTT on 8/3/2018.
 */

public class HomeFragment extends Fragment implements HomeContract.View{

    private View mView;
    private HomeContract.Presenter mHomePresenter;
    private RecyclerView mRvCategory, mRvBanner, mRvGenre;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHomePresenter.loadData();
    }

    @Override
    public void showListCategoryMovies(ArrayList<CategoryMovie> categoryMovies) {

    }

    @Override
    public void showBanners(ArrayList<String> arrayBanner) {

    }

    @Override
    public void showGenres(ArrayList<Genres> genresArrayList) {

    }

    @Override
    public void showDialogLoadDataFromServer() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mHomePresenter = checkNotNull(presenter);
    }
}
