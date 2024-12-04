package com.wons.memotalk.mainactivity.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.entity.memoList.MainMemoList;

import java.util.List;

@Dao
public interface MainMemoListDao {

    @Query("SELECT * FROM MemoRoom WHERE id = :id")
    List<MainMemoList> getAll(Long id);
}
