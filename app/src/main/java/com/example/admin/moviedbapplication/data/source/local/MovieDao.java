package com.example.admin.moviedbapplication.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.List;

/**
 * Created by TamTT on 8/21/2018.
 */
@Dao
public interface MovieDao {
    String TABLE_MOVIE = "Movies";
    /**
     * Select all movie from movie table.
     *
     * @return all movie.
     */
    @Query("SELECT * FROM " + TABLE_MOVIE)
    List<Movie> getAllMovies();

    /**
     * Add movie into favorites.
     *
     * @param movie the movie to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovie(Movie movie);

    /**
     * Remove movie from favorites.
     *
     * @param movieId the movie's id to be removed.
     */
    @Query("DELETE FROM Movies WHERE id = :movieId")
    int deleteMovie(String movieId);
}
