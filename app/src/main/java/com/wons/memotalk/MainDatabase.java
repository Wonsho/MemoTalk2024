package com.wons.memotalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.entity.memo_data.MemoFile;
import com.wons.memotalk.entity.memo_data.MemoImg;
import com.wons.memotalk.entity.memo_data.MemoText;
import com.wons.memotalk.entity.memo_data.MemoTodo;
import com.wons.memotalk.entity.memo_data.MemoUrl;
import com.wons.memotalk.memotalkactivity.dao.MemoRoomDao;
import com.wons.memotalk.mainactivity.dao.TabDao;

@Database(entities = {Tab.class, MemoRoom.class, MemoText.class, MemoImg.class, MemoTodo.class, MemoUrl.class, MemoFile.class, MemoItem.class},version = 2)
public abstract class MainDatabase extends RoomDatabase {
    public abstract TabDao tabDao();
    public abstract MemoRoomDao memoRoomDao();
}
