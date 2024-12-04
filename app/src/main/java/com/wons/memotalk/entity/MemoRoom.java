package com.wons.memotalk.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;

@Entity
public class MemoRoom {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public Long tabId;
    public boolean fixed;
    public String title;
}
