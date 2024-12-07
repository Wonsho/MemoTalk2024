package com.wons.memotalk.mainactivity.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.entity.memoList.MainMemoList;

import java.util.List;

@Dao
public interface MainMemoListDao {

    @Query("SELECT * FROM MemoRoom WHERE tabId = :id")
    List<MainMemoList> getAll(Long id);

    //    @Query("SELECT id FROM memoroom ORDER BY id DESC LIMIT 1")
    @Query("SELECT * FROM memoitem WHERE memoRoomId = :memoRoomId ORDER BY id DESC LIMIT 1")
    MemoItem getLastMemoItem(Long memoRoomId);

    @Query("SELECT value FROM memotext WHERE memoId = :memoId ")
    String getTextValue(Long memoId);

}
