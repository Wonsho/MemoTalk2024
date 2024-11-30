package com.wons.memotalk.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mark {
    @PrimaryKey
    public Long id;
    private Long memoId;
    private Boolean check;
    private Boolean exclamation;
}
