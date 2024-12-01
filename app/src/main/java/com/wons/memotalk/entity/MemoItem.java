package com.wons.memotalk.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoItem {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public Long memoRoomId;
    public Long valueCategory;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    public String date;
    private Boolean check;
    private Boolean exclamation;
}
