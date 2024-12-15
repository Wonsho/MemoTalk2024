package com.wons.memotalk.entity.memo_data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class TodoList {
    @PrimaryKey
    private long memoId;
    private String title;
}
