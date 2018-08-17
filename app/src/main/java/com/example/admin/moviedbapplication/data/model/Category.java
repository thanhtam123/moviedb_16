package com.example.admin.moviedbapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/6/2018.
 */

public class Category implements Parcelable{
    private String mCategeryName;
    private ArrayList<Movie> mCategoryMovie;

    public Category(String categeryName, ArrayList<Movie> categoryMovie) {
        mCategeryName = categeryName;
        mCategoryMovie = categoryMovie;
    }

    protected Category(Parcel in) {
        mCategeryName = in.readString();
        mCategoryMovie = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

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

    @Override
    public String toString() {
        return "Category{" +
                "mCategeryName='" + mCategeryName + '\'' +
                ", mCategoryMovie=" + mCategoryMovie +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCategeryName);
        parcel.writeTypedList(mCategoryMovie);
    }
}
