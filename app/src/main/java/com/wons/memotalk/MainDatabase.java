package com.wons.memotalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.memotalk.dao.ListItemDao;
import com.wons.memotalk.dao.TabDao;
import com.wons.memotalk.entity.ListItem;
import com.wons.memotalk.entity.TabItem;

@Database(entities = {TabItem.class, ListItem.class},version = 1)
public abstract class MainDatabase extends RoomDatabase {
    public abstract TabDao tabDao();
    public abstract ListItemDao listItemDao();
}
