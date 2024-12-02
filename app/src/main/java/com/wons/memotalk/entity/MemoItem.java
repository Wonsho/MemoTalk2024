package com.wons.memotalk.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoItem {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public Long memoRoomId;
    public Integer valueCategory;
    public Long date;
    public Boolean check;
    public Boolean exclamation;
}
