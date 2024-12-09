package com.wons.memotalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.memotalk.entity.Tab;

@Database(entities = {Tab.class},version = 1)
public abstract class MainDatabase extends RoomDatabase {
}
