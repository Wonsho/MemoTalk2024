package com.wons.memotalk.memotalkactivity.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Transaction;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.IconId;
import com.wons.memotalk.entity.ListIcon;
import com.wons.memotalk.entity.MemoRoom;

public class MemoViewModel extends ViewModel {
    private MutableLiveData<MemoRoomInfo> memoRoomInfo;

    class MemoRoomInfo {
        long id;
        String title;
        long fragmentsId;
    }

    public void setMemoDataFromDataBase(Context context, Long fragmentsId, Long memoId) {
        if (this.memoRoomInfo == null) {
            this.memoRoomInfo = new MutableLiveData<>();
            memoRoomInfo.setValue(new MemoRoomInfo());
        }

        MemoRoom memoRoom = Database.getDatabase(context).memoRoomDao().getMemoRoom(memoId);

        String title;
        if (memoRoom == null) {
            Long lastId = Database.getDatabase(context).memoRoomDao().getMemoRoomLastPk();
            Log.i("MemoViewModel", "Memo data is null");
            title = context.getString(R.string.default_memo_name);

            if (lastId != null) {
                title += " " + lastId;
            }
        } else {
            title = memoRoom.title;
        }

        this.memoRoomInfo.getValue().id = memoId;
        this.memoRoomInfo.getValue().title = title;
        this.memoRoomInfo.getValue().fragmentsId = fragmentsId;
    }

    @Transaction
    public void saveMemoRoom(Context context) {
        MemoRoom memoRoom = new MemoRoom();
        memoRoom.title = this.memoRoomInfo.getValue().title;
        memoRoom.tabId = this.memoRoomInfo.getValue().fragmentsId;
        memoRoom.fixed = false;
        Long memoRoomId = Database.getDatabase(context).memoRoomDao().save(memoRoom);
        ListIcon icon = new ListIcon();
        icon.listId = memoRoomId;
        icon.iconId = IconId.DEFAULT_ICON.getId();
        Database.getDatabase(context).memoRoomDao().save(icon);
        Log.i("MemoViewmodel", "MemoRoom is saved memo room id is " + memoRoomId);
        this.memoRoomInfo.getValue().id = memoRoomId;
    }

    public Long getMemoRoomId() {
        return this.memoRoomInfo.getValue().id;
    }

    public String getMemoTitle() {
        return this.memoRoomInfo.getValue().title;
    }

}
