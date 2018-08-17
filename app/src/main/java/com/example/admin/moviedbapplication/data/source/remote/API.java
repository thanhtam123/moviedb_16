package com.example.admin.moviedbapplication.data.source.remote;

/**
 * Created by TamTT on 8/7/2018.
 */

public interface API {
    String BASE_URL = "https://api.themoviedb.org/3";
    String API_KEY = "?api_key=";
    String PAGE = "&page=";
    String MOVIE = "/movie";
    String SLASH = "/";
    String DISCOVER = "/discover";
    String GENRES = "&with_genres=";
    String GENRE_LIST = "/genre/list";
    String SEARCH = "/search";
    String QUERY = "&query=";
    String VIDEO = "/videos";
    String CREDITS = "/credits";
    String CREDIT = "/credit";
}
