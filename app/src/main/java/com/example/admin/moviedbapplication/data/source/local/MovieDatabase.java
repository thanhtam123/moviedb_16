package com.example.admin.moviedbapplication.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.utils.Constants;

/**
 * Created by TamTT on 8/21/2018.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase INSTANCE;

    public abstract MovieDao movieDao();

    public static MovieDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, Constants.DATABASE_NAME).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
