package com.wons.memotalk.mainactivity.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.memotalk.entity.Tab;

@Database(entities = {Tab.class}, version =  1)
public abstract class TabDatabase extends RoomDatabase {
    public abstract TabRepository repository();
}
