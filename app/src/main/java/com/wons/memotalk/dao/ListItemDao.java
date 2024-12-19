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

    @Query("SELECT roomId FROM listitem ORDER BY roomId DESC LIMIT 1")
    Long getLastPk();

    @Insert
    Long insert(ListItem listItem);

    @Update
    void update(ListItem listItem);

}
