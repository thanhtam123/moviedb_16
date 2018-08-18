package com.example.admin.moviedbapplication.data.source.remote.cast;

import com.example.admin.moviedbapplication.BuildConfig;
import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.API;

import java.util.List;

/**
 * Created by TamTT on 8/15/2018.
 */

public class CastRemoteDataSource implements CastDataSource{

    private static CastRemoteDataSource sInstance;

    private CastRemoteDataSource() {
    }

    public static CastRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new CastRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getCreditsOfMovie(String idMovie, Callback<List<Cast>> callback) {
        String url = API.BASE_URL + API.MOVIE +API.SLASH + idMovie +
                API.CREDITS + API.API_KEY + BuildConfig.ApiKey;
        new CastRemoteAsyntask(callback).execute(url);
    }

    @Override
    public void getCredit(String id, Callback<Cast> callback) {

    }
}
