package com.example.admin.moviedbapplication.screen.actor_detail;

import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/15/2018.
 */

public interface DetailActorContract {
    interface View{

        void showMovies(ArrayList<Movie> movies);

        void showMoviesFail(Exception e);

    }
    interface Presenter{
        void loadMovies(String idActor);
    }
}
