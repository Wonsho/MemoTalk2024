package com.wons.memotalk;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wons.memotalk.dao.ListItemDao;
import com.wons.memotalk.dao.MemoDataDao;
import com.wons.memotalk.dao.TabDao;
import com.wons.memotalk.entity.ListItem;
import com.wons.memotalk.entity.TabItem;
import com.wons.memotalk.entity.memo_data.File;
import com.wons.memotalk.entity.memo_data.Img;
import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.Text;
import com.wons.memotalk.entity.memo_data.Url;
import com.wons.memotalk.entity.memo_data.todo.Todo;
import com.wons.memotalk.entity.memo_data.todo.TodoItem;

@Database(entities = {TabItem.class, ListItem.class, MemoData.class, Text.class, Url.class
, Img.class, File.class, TodoItem.class, Todo.class},version = 1)
public abstract class MainDatabase extends RoomDatabase {
    public abstract TabDao tabDao();
    public abstract ListItemDao listItemDao();
    public abstract MemoDataDao memoDataDao();
}
