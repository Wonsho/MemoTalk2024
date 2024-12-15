package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoData {
    @PrimaryKey
    public Long id;
    private Long memoRoomId;

    private Integer memoType;
    private Long date;
    private Boolean check;
    private Boolean exclamation;
}
