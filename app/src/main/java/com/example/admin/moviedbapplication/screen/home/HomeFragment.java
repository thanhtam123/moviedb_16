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
import android.view.WindowManager;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.screen.genre.GenresActivity;
import com.example.admin.moviedbapplication.screen.home.adapter.CategoryAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.GenresAdapter;
import com.example.admin.moviedbapplication.screen.home.adapter.OnGenreItemClickListener;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class HomeFragment extends Fragment implements
        HomeContract.View, OnGenreItemClickListener {

    private HomePresenter mPresenter;
    private RecyclerView recyclerCategory, recyclerGenre;
    private ProgressDialog progressDialog;
    private CategoryAdapter adapter;
    private ArrayList<Category> mCategories;
    private ArrayList<Genre> mGenres;
    private int page;

    public HomeFragment() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(savedInstanceState != null){
            mCategories = savedInstanceState.getParcelableArrayList(Constants.KEY_CATEGORY);
            mGenres = savedInstanceState.getParcelableArrayList(Constants.KEY_GENRES);
            showCategory(mCategories, mGenres);
        }else {
            page = 1;
            mPresenter.loadGenres();
            mPresenter.loadCategories(1);
            showLoadingAnimation();
        }
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.KEY_CATEGORY, mCategories);
        outState.putParcelableArrayList(Constants.KEY_GENRES, mGenres);
    }

    @Override
    public void showCategory(ArrayList<Category> categories, ArrayList<Genre> genres) {
        adapter = new CategoryAdapter(getActivity());
        recyclerCategory.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false));
        recyclerCategory.setAdapter(adapter);
        adapter.updateData(categories);

        GenresAdapter adapter = new GenresAdapter(getActivity());
        adapter.setOnItemClickedListener(this);
        recyclerGenre.setLayoutManager(
                new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false));
        recyclerGenre.setAdapter(adapter);
        adapter.updateData(genres);
        progressDialog.dismiss();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void showLoadDataMainFail(Exception e) {
        progressDialog.dismiss();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
    public boolean idActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onGenreClick(Genre genre) {
        Intent intent = new Intent(getContext(), GenresActivity.class);
        intent.putExtra(Constants.EXTRA_GENRE, genre);
        startActivity(intent);
    }
}
