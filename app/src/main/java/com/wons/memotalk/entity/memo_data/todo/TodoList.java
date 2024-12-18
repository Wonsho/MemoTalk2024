package com.wons.memotalk.entity.memo_data.todo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TodoList {
    @Embedded
    public Todo todo;
    @Relation(
            parentColumn = "memoId",
            entityColumn = "memoId"
    )
    public List<TodoItem> todoItemList;
}
