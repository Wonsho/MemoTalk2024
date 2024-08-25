package com.wons.memotalk;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wons.memotalk.mainactivity.repository.TabDatabase;
import com.wons.memotalk.mainactivity.repository.TabRepository;

public class DataBase {
    private static TabDatabase repository;

    public static TabRepository loadTabRepository(Context context) {
        if (repository == null) {
            repository = Room.databaseBuilder(context, TabDatabase.class, "tab_database").allowMainThreadQueries().build();
        }
        return repository.repository();
    }
}
