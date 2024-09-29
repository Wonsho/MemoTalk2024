package com.wons.memotalk;

import android.content.Context;

import androidx.room.Room;

public class Database {
    private static MainDatabase database;

    public static MainDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, MainDatabase.class, "main-database")
                    .allowMainThreadQueries().build();
        }
        return database;
    }
}