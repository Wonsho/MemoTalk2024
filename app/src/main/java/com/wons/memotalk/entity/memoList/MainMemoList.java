package com.wons.memotalk.entity.memoList;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.wons.memotalk.entity.ListIcon;
import com.wons.memotalk.entity.MemoRoom;

public class MainMemoList {
    @Embedded public MemoRoom memoRoom;
    @Relation(
            parentColumn = "id",
            entityColumn = "listId"
    )
    public ListIcon listIcon;
}
