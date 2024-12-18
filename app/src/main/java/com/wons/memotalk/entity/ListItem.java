package com.wons.memotalk.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListItem {
    @PrimaryKey(autoGenerate = true)
    public long roomId;

    public Long listId;
    public int iconId;
    public String title;
    public Boolean fix;

}
