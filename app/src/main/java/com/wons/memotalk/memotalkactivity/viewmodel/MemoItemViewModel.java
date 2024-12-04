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
import java.util.Arrays;

public class MemoItemViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MemoData>> memoDataList;


    public ArrayList<MemoData> getMemoData(Context context, Long roomId) {
        if (memoDataList == null) {
            memoDataList = new MutableLiveData<>();
            memoDataList.setValue(new ArrayList<>());
           ArrayList<MemoItem> list =  getMemoItemFromDataBase(context, roomId);
           setMemoItemsToMemoDataList(context,list);
        }
        //todo 메모 데이터 만드는 코드 작성
        return this.memoDataList.getValue();
    }

    private ArrayList<MemoItem> getMemoItemFromDataBase(Context context, Long RoomId) {
        return (ArrayList<MemoItem>) Database.getDatabase(context).memoRoomDao().getMemoItemByRoomId(RoomId);
    }


    private void setMemoItemsToMemoDataList(Context context, ArrayList<MemoItem> items) {
        for (MemoItem item : items) {
            switch (MemoDataType.fromTypeCode(item.valueCategory)) {
                case TEXT: {
                    //todo 데이타베이스에서 text 정보 가져옴
                    MemoText memoText = Database.getDatabase(context).memoRoomDao().getMemoText(item.id);
                    MemoData memoData = new MemoData(item.id, item.valueCategory, memoText, item.date,  item.check, item.exclamation);
                    this.memoDataList.getValue().add(memoData);
                }
                case TODO: {
                    //todo 데이터 베이스에서 todo 정보 가져옴
                }
                case URL: {
                    //todo 데이터타베이스 에서 url 정보 가져옴
                }
                case IMAGE: {
                    //todo 데이터 베이스에서 이미지 정보 가져옴
                }
                case FILE: {
                    //todo 데이터 베이스에서 파일 정보 가져옴
                }
            }
        }
    }


    @Transaction
    public void saveTextMemo(Context context, String value, Long memoRoomId) {
        //todo save text
        MemoItem memoItem = getMemoItem(MemoDataType.TEXT, memoRoomId);
        Long memoId = Database.getDatabase(context).memoRoomDao().save(memoItem);
        MemoText memoText = new MemoText();
        memoText.value = value;
        memoText.memoId = memoId;
        Database.getDatabase(context).memoRoomDao().save(memoText);
        memoItem.id = memoId;
        ArrayList<MemoItem> arr = new ArrayList<>();
        arr.add(memoItem);
        setMemoItemsToMemoDataList(context, arr);
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
