package com.example.admin.moviedbapplication.data.source.remote.movie;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/6/2018.
 */

public class MovieRemoteAsynTask extends AsyncTask<String, Void, List<Movie>> {
    private Callback<Category> mCallback;
    private Exception mException;
    private String mType;

    public MovieRemoteAsynTask(Callback<Category> callback, String type) {
        mCallback = callback;
        mType = type;
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
            Category category = new Category(mType.replace('_', ' ').toUpperCase(), new ArrayList<Movie>(movies));
            mCallback.onGetDataSuccess(category);
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }
}
