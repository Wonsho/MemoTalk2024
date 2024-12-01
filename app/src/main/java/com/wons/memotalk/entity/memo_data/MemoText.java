package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoText {
    @PrimaryKey
    public Long memoId;
    public String value;
}
