package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoFile {
    @PrimaryKey
    public Long id;
    public String pathAndName;
}
