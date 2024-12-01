package com.wons.memotalk.mainactivity.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.wons.memotalk.entity.Tab;

import java.util.List;

@Dao
public interface TabDao {
    @Query("SELECT * FROM TAB")
    List<Tab> getAll();
    @Insert
    void insert(Tab tab);
    @Delete
    void delete(Tab tab);
}
