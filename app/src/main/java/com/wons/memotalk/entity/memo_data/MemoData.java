package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoData {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public Long memoRoomId;

    public Integer memoType;
    public Long date;
    public Boolean check;
    public Boolean exclamation;
}
