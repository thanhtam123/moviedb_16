package com.example.admin.moviedbapplication.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.DataGenreClass;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/9/2018.
 */

public class GenresRemoteAsyntask extends AsyncTask<String, Void, List<Genre>> {
    private Callback<ArrayList<Genre>> mCallback;
    private Exception mException;

    public GenresRemoteAsyntask(Callback<ArrayList<Genre>> callback) {
        mCallback = callback;
    }

    @Override
    protected List<Genre> doInBackground(String... strings) {
        try {
            String json = Utils.getJSONStringFromURL(strings[0]);
            return Utils.parseJsonIntoGenre(json);
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
    protected void onPostExecute(List<Genre> genres) {
        super.onPostExecute(genres);
        if (mCallback == null) {
            return;
        }
        if (mException == null) {
            DataGenreClass.setData(new ArrayList<>(genres));
            mCallback.onGetDataSuccess(new ArrayList<>(genres));
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }
}
