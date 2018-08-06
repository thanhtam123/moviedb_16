package com.example.admin.moviedbapplication.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;

import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public class MovieRemoteAsynTask extends AsyncTask<String, Void, List<Movie>> {
    private Callback<List<Movie>> mCallback;

    public MovieRemoteAsynTask(Callback<List<Movie>> callback) {
        mCallback = callback;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if (mCallback != null) {
            mCallback.onGetDataSuccess(movies);
        }
    }
}
