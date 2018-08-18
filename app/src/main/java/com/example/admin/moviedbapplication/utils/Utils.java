package com.example.admin.moviedbapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.model.Genre;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.Video;

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
    public static Video parseJsonIntoVideo(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        JSONArray dataArray = root.getJSONArray(Video.JsonVideoKey.RESULTS);
        JSONObject videoObject = dataArray.getJSONObject(0);
        String id = videoObject.getString(Video.JsonVideoKey.ID);
        String key = videoObject.getString(Video.JsonVideoKey.KEY);
        return new Video(id, key);
    }
    public static List<Cast> parseJsonIntoCasts(String json) throws JSONException{
        JSONObject root = new JSONObject(json);
        JSONArray dataArray = root.getJSONArray(Cast.ActorJsonKey.CAST);
        List<Cast> casts = new ArrayList<>();
        for(int i = 0; i<dataArray.length(); i++){
            JSONObject cast = dataArray.getJSONObject(i);
            Cast castOb = new Cast();
            castOb.setCastId(cast.getString(Cast.ActorJsonKey.CAST_ID));
            castOb.setCharacter(cast.getString(Cast.ActorJsonKey.CHARACTER));
            castOb.setCreditId( cast.getString(Cast.ActorJsonKey.CREDIT_ID));
            castOb.setGender(cast.getString(Cast.ActorJsonKey.GENDER));
            castOb.setId(cast.getString(Cast.ActorJsonKey.ID));
            castOb.setName(cast.getString(Cast.ActorJsonKey.NAME));
            castOb.setProfilePath(cast.getString(Cast.ActorJsonKey.PROFILE_PATH));
            casts.add(castOb);
        }
        return casts;
    }
    public static List<Movie> parseJsonIntoMoviesByActor(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        JSONObject person = root.getJSONObject(Movie.JsonKey.PERSON);
        JSONArray knownForArr = person.getJSONArray(Movie.JsonKey.KNOWN_FOR);
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<knownForArr.length(); i++){
            JSONObject jsonObject = (JSONObject) knownForArr.get(i);

            JSONArray genresArray = jsonObject.optJSONArray(Movie.JsonKey.GENRE_IDS);
            int[] mGenreIds;
            if(genresArray == null){
                JSONArray genres = jsonObject.optJSONArray(Movie.JsonKey.GENRES);
                mGenreIds = new int[genres.length()];
                for (int j = 0; j < genres.length(); j++) {
                    mGenreIds[j] = genres.getJSONObject(j).getInt(Movie.JsonKey.ID_GENRE);
                }
            }else {
                mGenreIds = new int[genresArray.length()];
                for (int j = 0; j < genresArray.length(); j++) {
                    mGenreIds[j] = genresArray.optInt(j);
                }
            }

            Movie movie = new Movie.Builder().setAdult(jsonObject.getBoolean(Movie.JsonKey.ADULT))
                    .setBackdropPath(jsonObject.getString(Movie.JsonKey.BACKDROP_PATH))
                    .setId(jsonObject.getString(Movie.JsonKey.ID))
                    .setOriginalLanguage(jsonObject.getString(Movie.JsonKey.ORIGINAL_LANGUAGE))
                    .setOriginalTitle(jsonObject.getString(Movie.JsonKey.ORIGINAL_TITLE))
                    .setOverview(jsonObject.getString(Movie.JsonKey.OVERVIEW))
                    .setPosterPath(jsonObject.getString(Movie.JsonKey.POSTER_PATH))
                    .setRelaseDate(jsonObject.getString(Movie.JsonKey.RELEASE_DATE))
                    .setTitle(jsonObject.getString(Movie.JsonKey.TITLE))
                    .setGenreIds(mGenreIds)
                    .setVideo(jsonObject.getBoolean(Movie.JsonKey.VIDEO))
                    .setVoteAverage(jsonObject.getDouble(Movie.JsonKey.VOTE_AVERAGE))
                    .setVoteCount(jsonObject.getInt(Movie.JsonKey.VOTE_COUNT))
                    .setPopularity(jsonObject.getInt(Movie.JsonKey.POPULARITY)).create();

            movies.add(movie);
        }
        return movies;
    }

    public static void initProgressDialog(Context context, ProgressDialog progressDialog){
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.text_loading));
        progressDialog.show();
        final ProgressDialog finalProgressDialog = progressDialog;
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                if(finalProgressDialog.isShowing())
                    finalProgressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);
    }
    public static void dismissProgressDialog(ProgressDialog progressDialog){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
