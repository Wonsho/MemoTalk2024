package com.wons.memotalk.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListIcon {
    @PrimaryKey
    public Long listId;
    public Integer iconId;
}
