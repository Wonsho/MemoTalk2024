package com.wons.memotalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.memotalkactivity.dao.MemoRoomDao;
import com.wons.memotalk.mainactivity.dao.TabDao;

@Database(entities = {Tab.class, MemoRoom.class},version = 1)
public abstract class MainDatabase extends RoomDatabase {
    public abstract TabDao tabDao();
    public abstract MemoRoomDao memoRoomDao();
}
