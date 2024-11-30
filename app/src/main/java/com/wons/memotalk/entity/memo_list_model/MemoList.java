package com.wons.memotalk.entity.memo_list_model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.wons.memotalk.entity.ListData;
import com.wons.memotalk.entity.ListIcon;

public class MemoList {
    @Embedded
    public ListData listData;
    @Relation(
            parentColumn = "id",
            entityColumn = "listId"
    )
    public ListIcon listIcon;
}
