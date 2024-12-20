package com.wons.memotalk.dao;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wons.memotalk.entity.ListItem;

import java.util.List;

@Dao
public interface ListItemDao {

    @Query("SELECT * FROM listitem WHERE roomId = :roomId")
    ListItem getListItemByRoomId(Long roomId);

    @Query("SELECT CAST(SUBSTR(title, LENGTH(:keyword) + 2) AS INTEGER) FROM listitem WHERE title LIKE :keyword || ' %' AND CAST(SUBSTR(title, LENGTH(:keyword) + 2) AS INTEGER) > 0")
    List<Integer> getMemosWithKeyword(String keyword);

    @Insert
    Long insert(ListItem listItem);

    @Update
    void update(ListItem listItem);

}
