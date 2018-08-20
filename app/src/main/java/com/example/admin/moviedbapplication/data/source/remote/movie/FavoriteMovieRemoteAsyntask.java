package com.example.admin.moviedbapplication.data.source.remote.movie;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.API;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/13/2018.
 */

public class FavoriteMovieRemoteAsyntask extends AsyncTask<Void, Void, List<Movie>> {
    private Callback<List<Movie>> mCallback;
    private Exception mException;
    private ArrayList<String> mIds;
    public FavoriteMovieRemoteAsyntask(ArrayList<String> ids, Callback<List<Movie>> callback) {
        mIds = ids;
        mCallback = callback;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            for (int i=0; i<mIds.size(); i++){
                String url = API.BASE_URL + API.MOVIE + API.SLASH + mIds.get(i) + API.API_KEY
                        + BuildConfig.ApiKey;
                String json = Utils.getJSONStringFromURL(url);
                movies.add(Utils.parseJsonIntoMovie(json));
            }
            return movies;
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
