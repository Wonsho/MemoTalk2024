package com.wons.memotalk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.memotalk.entity.Tab;

import java.util.List;

@Dao
public interface TabDao {
    @Query("SELECT * FROM tab")
    LiveData<List<Tab>> getAll();

    @Insert
    void insert(Tab tab);

    @Update
    void update(Tab tab);

}
