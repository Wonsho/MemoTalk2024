package com.wons.memotalk.dao;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.wons.memotalk.entity.ListItem;

@Dao
public interface ListItemDao {
    @Query("SELECT * FROM listitem WHERE roomId = :roomId")
    MutableLiveData<ListItem> getListItemByRoomId(long roomId);

    @Query("SELECT roomId FROM listitem ORDER BY roomId DESC LIMIT 1")
    Long getLastPk();

}
