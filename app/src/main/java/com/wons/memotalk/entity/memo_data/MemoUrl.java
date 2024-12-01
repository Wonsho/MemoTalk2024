package com.wons.memotalk.entity.memo_data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoUrl {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String path;
}
