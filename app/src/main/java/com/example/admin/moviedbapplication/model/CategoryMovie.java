package com.example.admin.moviedbapplication.model;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/3/2018.
 */

public class CategoryMovie {
    private String mCategoryName;
    private ArrayList<MovieObject> mMovieArrayList;

    public CategoryMovie(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public ArrayList<MovieObject> getmMovieArrayList() {
        return mMovieArrayList;
    }

    public void setmMovieArrayList(ArrayList<MovieObject> mMovieArrayList) {
        this.mMovieArrayList = mMovieArrayList;
    }
}
