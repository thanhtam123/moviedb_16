package com.example.admin.moviedbapplication.screen.actor;

import com.example.admin.moviedbapplication.data.model.Cast;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/15/2018.
 */

public interface ActorContract {
    interface View{
        void showListCasts(ArrayList<Cast> casts);

        void showListCastsFail(Exception e);
    }

    interface Presenter{
         void loadCasts(String idMovie);
    }
}
