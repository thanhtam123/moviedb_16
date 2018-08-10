package com.example.admin.moviedbapplication.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by TamTT on 8/10/2018.
 */

public class SingleMovieRemoteAsyntask extends AsyncTask<String, Void, Movie> {

    private Callback<Movie> mCallback;
    private Exception mException;

    public SingleMovieRemoteAsyntask(Callback<Movie> callback) {
        mCallback = callback;
    }

    @Override
    protected Movie doInBackground(String... strings) {
        try {
            String json = Utils.getJSONStringFromURL(strings[0]);
            return Utils.parseJsonIntoMovie(json);
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } catch (JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);
        if (mCallback == null) {
            return;
        }
        if (mException == null) {
            mCallback.onGetDataSuccess(movie);
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }
}
