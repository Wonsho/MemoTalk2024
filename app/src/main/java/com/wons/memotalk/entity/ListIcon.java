package com.wons.memotalk.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListIcon {
    @PrimaryKey
    public Long id;
    public Long listId;
    public String iconPath;
}
