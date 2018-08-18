package com.example.admin.moviedbapplication.screen.actor;

import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.cast.CastRemoteDataSource;
import com.example.admin.moviedbapplication.data.source.remote.cast.CastRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/15/2018.
 */

public class ActorPresenter implements ActorContract.Presenter {
    private CastRepository mCastRepository;
    private ActorContract.View mView;

    public ActorPresenter(ActorContract.View view) {
        mView = view;
        mCastRepository = CastRepository.getInstance(CastRemoteDataSource.getInstance());
    }

    @Override
    public void loadCasts(String idMovie) {
        mCastRepository.getCreditsOfMovie(idMovie, new Callback<List<Cast>>() {
            @Override
            public void onGetDataSuccess(List<Cast> data) {
                mView.showListCasts(new ArrayList<>(data));
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mView.showListCastsFail(e);
            }
        });
    }
}
