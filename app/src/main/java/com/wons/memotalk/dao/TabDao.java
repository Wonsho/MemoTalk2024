package com.wons.memotalk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.memotalk.entity.TabItem;

import java.util.List;

@Dao
public interface TabDao {
    @Query("SELECT * FROM TabItem")
    LiveData<List<TabItem>> getAll();

    @Insert
    void insert(TabItem tabItem);

    @Update
    void update(TabItem tabItem);

}
