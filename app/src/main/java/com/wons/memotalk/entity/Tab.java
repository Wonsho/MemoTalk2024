package com.wons.memotalk.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tab {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String tabName;
}
