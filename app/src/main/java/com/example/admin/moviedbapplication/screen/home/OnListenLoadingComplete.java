package com.example.admin.moviedbapplication.screen.home;

import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/8/2018.
 */

public interface OnListenLoadingComplete {

    void loadDone(String asyncClassName, Category category, ArrayList<Genre> genres);

    void loadFail(Exception e);

}
