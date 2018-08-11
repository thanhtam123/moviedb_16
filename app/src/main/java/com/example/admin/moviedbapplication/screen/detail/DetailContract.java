package com.example.admin.moviedbapplication.screen.detail;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.data.model.Video;

/**
 * Created by TamTT on 8/10/2018.
 */

public class DetailContract {

    interface View{

        void showMovie(Movie movie);

        void showVideoLoadFail(String e);

        void playVideo(Video video);
    }

    interface Presenter{

        void loadVideo(String id);
    }
}
