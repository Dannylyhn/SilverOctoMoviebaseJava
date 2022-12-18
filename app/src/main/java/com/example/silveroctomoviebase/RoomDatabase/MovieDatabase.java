package com.example.silveroctomoviebase.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static volatile MovieDatabase Instance;

    public static MovieDatabase getInstance(Context context){
        if(Instance==null){
            synchronized (MovieDatabase.class){
                if(Instance==null){
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movies.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return Instance;
    }
}

