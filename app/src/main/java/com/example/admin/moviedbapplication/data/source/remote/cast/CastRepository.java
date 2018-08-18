package com.example.admin.moviedbapplication.data.source.remote.cast;

import android.support.annotation.NonNull;

import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.source.Callback;

import java.util.List;

/**
 * Created by TamTT on 8/15/2018.
 */

public class CastRepository implements CastDataSource{
    private static CastRepository sInstance;
    @NonNull
    private CastDataSource mRemoteDataSource;


    private CastRepository() {
    }

    private CastRepository(CastDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static CastRepository getInstance(@NonNull CastDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new CastRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getCreditsOfMovie(String idMovie, Callback<List<Cast>> callback) {
        mRemoteDataSource.getCreditsOfMovie(idMovie, callback);
    }

    @Override
    public void getCredit(String id, Callback<Cast> callback) {

    }
}
