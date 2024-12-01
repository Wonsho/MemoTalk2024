package com.wons.memotalk.entity.memo_data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemoImg {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public Long memoId;
    public String pathAndName;
}
