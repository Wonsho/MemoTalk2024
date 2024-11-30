package com.wons.memotalk.entity.memo_model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.wons.memotalk.entity.Mark;
import com.wons.memotalk.entity.Text;

public class MemoText {
    @Embedded
    public Text text;
    @Relation(
            parentColumn = "id",
            entityColumn = "memoId"
    )
    public Mark mark;
}
