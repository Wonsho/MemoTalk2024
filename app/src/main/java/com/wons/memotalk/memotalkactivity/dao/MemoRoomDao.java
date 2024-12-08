package com.wons.memotalk.memotalkactivity.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wons.memotalk.entity.ListIcon;
import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.entity.memo_data.MemoText;

import java.util.List;

@Dao
public interface MemoRoomDao {
    @Query("SELECT id FROM memoroom ORDER BY id DESC LIMIT 1")
    Long getMemoRoomLastPk();
    @Query("SELECT * FROM memoroom WHERE id = :id")
    MemoRoom getMemoRoom(Long id);

    //해당 메모 리스트 가져오기
    @Query("SELECT * FROM memoitem WHERE memoRoomId = :id ORDER BY id ")
    List<MemoItem> getMemoItemByRoomId(Long id);

    @Query("SELECT * FROM MemoText WHERE memoId = :id")
    MemoText getMemoText(Long id);
    //해당 메모 아이템의 아이템 데이터 가져오기

    @Insert
    Long save(MemoItem memoItem);

    @Insert
    Long save(MemoRoom memoRoom);

    @Insert
    void save(MemoText memoText);

    @Insert
    void save(ListIcon listIcon);

}
