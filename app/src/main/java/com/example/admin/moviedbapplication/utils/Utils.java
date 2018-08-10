package com.example.admin.moviedbapplication.utils;

import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;

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
 * Created by TamTT on 8/7/2018.
 */

public class Utils {
    public static String getJSONStringFromURL(String urlString) throws IOException, JSONException {

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

    public static List<Movie> parseJson(String json) throws JSONException {
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
    public static List<Genre> parseJsonIntoGenre(String json) throws JSONException {
        ArrayList<Genre> genreArrayList = new ArrayList<>();
        JSONObject root = new JSONObject(json);
        JSONArray dataArray = root.getJSONArray(Movie.JsonKey.GENRES);
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject object = dataArray.getJSONObject(i);
            String id = object.getString(Movie.JsonKey.ID_GENRE);
            String name = object.getString(Movie.JsonKey.NAME_GENRE);
            genreArrayList.add(new Genre(id,name));
        }
        return genreArrayList;
    }
    public static Movie parseJsonIntoMovie(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        return new Movie(root);
    }
}
