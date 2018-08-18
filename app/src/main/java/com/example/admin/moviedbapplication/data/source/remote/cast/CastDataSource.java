package com.example.admin.moviedbapplication.data.source.remote.cast;

import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.source.Callback;

import java.util.List;

/**
 * Created by TamTT on 8/15/2018.
 */

public interface CastDataSource {

    void getCreditsOfMovie(String idMovie, Callback<List<Cast>> callback);

    void getCredit(String id, Callback<Cast> callback);
}
