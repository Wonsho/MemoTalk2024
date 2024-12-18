package com.wons.memotalk.entity.memo_data.todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long memoId;
    public String title;
}
