package com.example.admin.moviedbapplication.screen.actor_detail;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.data.source.remote.movie.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/15/2018.
 */

public class DetailActorPresenter implements DetailActorContract.Presenter {

    private MovieRepository mMovieRepository;
    private DetailActorContract.View mView;

    public DetailActorPresenter(DetailActorContract.View viewDetail, MovieRepository movieRepository) {
        mView = viewDetail;
        mMovieRepository = movieRepository;
    }

    @Override
    public void loadMovies(String idActor) {
        mMovieRepository.getMovieByActor(idActor, new Callback<List<Movie>>() {
            @Override
            public void onGetDataSuccess(List<Movie> data) {
                mView.showMovies(new ArrayList<>(data));
            }

            @Override
            public void onGetDataFailure(Exception e) {
                mView.showMoviesFail(e);
            }
        });
    }
}
