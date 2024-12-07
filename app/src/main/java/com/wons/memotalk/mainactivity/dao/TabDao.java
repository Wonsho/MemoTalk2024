package com.wons.memotalk.mainactivity.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.entity.memo_data.MemoText;

import java.util.List;

@Dao
public interface TabDao {
    @Query("SELECT * FROM TAB")
    List<Tab> getAll();
    @Insert
    void insert(Tab tab);
    @Delete
    void delete(Tab tab);
    @Update
    void update(Tab tab);

    @Insert
    void getMemoRoomById(Long tabId);

}
