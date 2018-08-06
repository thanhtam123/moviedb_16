package com.example.admin.moviedbapplication.data.model;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/6/2018.
 */

public class Category {
    private String mCategeryName;
    private ArrayList<Movie> mCategoryMovie;

    public Category(String categeryName, ArrayList<Movie> categoryMovie) {
        mCategeryName = categeryName;
        mCategoryMovie = categoryMovie;
    }

    public String getCategeryName() {
        return mCategeryName;
    }

    public void setCategeryName(String categeryName) {
        mCategeryName = categeryName;
    }

    public ArrayList<Movie> getCategoryMovie() {
        return mCategoryMovie;
    }

    public void setCategoryMovie(ArrayList<Movie> categoryMovie) {
        mCategoryMovie = categoryMovie;
    }
}
