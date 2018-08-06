package com.example.admin.moviedbapplication.data.source;

/**
 * Created by TamTT on 8/6/2018.
 */

public interface Callback<T> {
    void onGetDataSuccess(T data);

    void onGetDataFailure(Exception e);
}
