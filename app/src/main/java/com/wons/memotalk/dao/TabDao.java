package com.wons.memotalk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wons.memotalk.entity.Tab;

import java.util.List;

@Dao
public interface TabDao {
    @Query("SELECT * FROM tab")
    LiveData<List<Tab>> getAll();

    @Insert
    void insert(Tab tab);

}
