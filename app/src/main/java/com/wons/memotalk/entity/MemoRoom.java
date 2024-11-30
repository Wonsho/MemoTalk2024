package com.wons.memotalk.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;

@Entity
public class MemoRoom {
    @PrimaryKey
    public Long id;
    public Long tabId;
    public Instant time;
    public String title;

}
