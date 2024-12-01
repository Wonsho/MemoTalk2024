package com.wons.memotalk.memotalkactivity.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.MemoRoom;

public class MemoViewModel extends ViewModel {
    private MutableLiveData<MemoData> memoData;

    class MemoData {
        long id;
        String title;
        long fragmentsId;
    }

    public void setMemoDataFromDataBase(Context context, Long fragmentsId, Long memoId) {
        if (this.memoData == null) {
            this.memoData = new MutableLiveData<>();
            memoData.setValue(new MemoData());
        }

        MemoRoom memoRoom = Database.getDatabase(context).memoRoomDao().getMemoRoom(memoId);
        if (memoRoom == null) {
            Long lastId = Database.getDatabase(context).memoRoomDao().getMemoRoomLastPk();

            Log.i("MemoViewModel", "Memo data is null");
            this.memoData.getValue().fragmentsId = fragmentsId;
            String title = context.getString(R.string.default_memo_name);
            if (lastId != null) {
                title += " " + lastId;
            }
            this.memoData.getValue().title = title;
        }
    }

    public String getMemoTitle() {
        return this.memoData.getValue().title;
    }

}
