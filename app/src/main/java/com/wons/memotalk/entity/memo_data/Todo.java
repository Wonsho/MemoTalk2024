package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    
    @PrimaryKey
    private long memoId;
    private Boolean check;
    private String value;
}
