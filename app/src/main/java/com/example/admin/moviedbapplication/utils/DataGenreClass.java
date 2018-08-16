package com.example.admin.moviedbapplication.utils;

import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/16/2018.
 */

public class DataGenreClass {
    private static ArrayList<Genre> mGenres = new ArrayList<>();

    public static Genre getGenres(int id){
        for (Genre genre : mGenres) {
            if(genre.getId().equals(String.valueOf(id))){
                return genre;
            }
        }
        return null;
    }

    public static List<Genre> getListGenres(int[] ids){
        ArrayList<Genre> genres = new ArrayList<>();
        for (int id : ids) {
            Genre genre = getGenres(id);
            if(genre != null){
                genres.add(genre);
            }
        }
        return genres;
    }

    public static String getGenreString(List<Genre> arrayList){
        String genreName = "";
        for (Genre genre: arrayList) {
            genreName += genre.getGenreName() + ", ";
        }
        return genreName.substring(0, genreName.length() - 2);
    }

    public static void setData(ArrayList<Genre> data) {
        DataGenreClass.mGenres = data;
    }
}
