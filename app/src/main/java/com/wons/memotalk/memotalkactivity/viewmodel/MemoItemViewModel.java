package com.wons.memotalk.memotalkactivity.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.ArrayRes;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Transaction;

import com.wons.memotalk.Database;
import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.MemoDataType;
import com.wons.memotalk.entity.memo_data.MemoText;
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;

public class MemoItemViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MemoData>> memoDataList;
    @Transaction
    public void saveTextMemo(Context context, String value, Long memoRoomId) {
        //todo save text
        Long memoId = Database.getDatabase(context).memoRoomDao().save(getMemoItem(MemoDataType.TEXT, memoRoomId));
        MemoText memoText = new MemoText();
        memoText.value = value;
        memoText.memoId = memoId;
        Database.getDatabase(context).memoRoomDao().save(memoText);
    }

    @Transaction
    public void saveImageMemo() {
        //todo save image
    }

    @Transaction
    public void saveFileMemo() {
        //todo save File
    }

    @Transaction
    public void saveTodoMemo() {
        //todo save Todo
    }

    @Transaction
    public void saveUrlMemo() {
        //todo save url
    }

    private MemoItem getMemoItem(MemoDataType dataType, Long memoRoomId) {
        MemoItem memoItem = new MemoItem();
        memoItem.exclamation = false;
        memoItem.valueCategory = dataType.getTypeCode();
        memoItem.check = false;
        memoItem.date = DateUtil.getDate();
        memoItem.memoRoomId = memoRoomId;
        return memoItem;
    }
}
