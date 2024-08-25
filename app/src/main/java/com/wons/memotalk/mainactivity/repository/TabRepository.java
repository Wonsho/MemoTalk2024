package com.wons.memotalk.mainactivity.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.wons.memotalk.entity.Tab;
import java.util.List;

@Dao
public interface TabRepository {
    @Query("SELECT * FROM Tab")
    List<Tab> getAll();

    @Insert
    void insertTab(Tab tab);

    @Delete
    void deleteTab(Tab tab);
}
