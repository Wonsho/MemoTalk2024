package com.wons.memotalk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.Text;
import com.wons.memotalk.entity.memo_data.todo.Todo;
import com.wons.memotalk.entity.memo_data.todo.TodoList;

import java.util.List;

@Dao
public interface MemoDataDao {
    @Query("SELECT * FROM memodata WHERE memoRoomId = :roomId")
    LiveData<List<MemoData>> getAllById(Long roomId);

    @Query("SELECT * FROM text WHERE memoId = (:ids)")
    LiveData<List<Text>> getAllTextById(List<Long> ids);

    @Query("SELECT * FROM img WHERE memoId = (:ids)")
    LiveData<List<Text>> getAllUrlById(List<Long> ids);

    @Query("SELECT * FROM file WHERE memoId = (:ids)")
    LiveData<List<Text>> getAllFileById(List<Long> ids);

    @Query("SELECT * FROM url WHERE memoId = (:ids)")
    LiveData<List<Text>> getAllImgById(List<Long> ids);

    @Transaction
    @Query("SELECT * FROM todo WHERE memoId = (:ids)")
    LiveData<List<TodoList>> getAllTodoById(List<Long> ids);
}