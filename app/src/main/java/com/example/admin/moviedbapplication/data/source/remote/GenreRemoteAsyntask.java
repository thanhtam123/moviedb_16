package com.example.admin.moviedbapplication.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by TamTT on 8/7/2018.
 */

public class GenreRemoteAsyntask extends AsyncTask<String, Void, List<Movie>> {
    private Callback<List<Movie>> mCallback;
    private Exception mException;

    public GenreRemoteAsyntask(Callback<List<Movie>> callback) {
        mCallback = callback;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        try {
            String json = Utils.getJSONStringFromURL(strings[0]);
            return Utils.parseJson(json);
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
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if (mCallback == null) {
            return;
        }
        if (mException == null) {
            mCallback.onGetDataSuccess(movies);
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }
}
