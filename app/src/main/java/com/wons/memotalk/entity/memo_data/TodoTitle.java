package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodoTitle {
    @PrimaryKey
    public Long memoId;
    public String title;
}
