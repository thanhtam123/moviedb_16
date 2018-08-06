package com.example.admin.moviedbapplication.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.Constants;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
            String json = getJSONStringFromURL(strings[0]);
            return parseJson(json);
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
            Category category = new Category(mType.replace('_',' ').toUpperCase(),new ArrayList<Movie>(movies));
            mCallback.onGetDataSuccess(category);
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }

    private String getJSONStringFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(Constants.REQUEST_METHOD_GET);
        urlConnection.setReadTimeout(Constants.READ_TIMEOUT);
        urlConnection.setConnectTimeout(Constants.CONNECTION_TIMEOUT);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();

        urlConnection.disconnect();
        return jsonString;
    }

    private List<Movie> parseJson(String json) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject root = new JSONObject(json);
        JSONArray dataArray = root.getJSONArray(Movie.JsonKey.RESULT);
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject object = dataArray.getJSONObject(i);
            Movie movie = new Movie(object);
            movies.add(movie);
        }
        return movies;
    }
}
