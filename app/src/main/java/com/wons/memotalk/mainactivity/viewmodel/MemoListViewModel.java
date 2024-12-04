package com.wons.memotalk.mainactivity.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.memoList.MainMemoList;
import com.wons.memotalk.entity.memoList.MainMemoListModel;
import com.wons.memotalk.entity.memo_data.MemoDataType;
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoListViewModel extends ViewModel {
    private MutableLiveData<HashMap<Long, ArrayList<MainMemoList>>>  memoList;

    public ArrayList<MainMemoListModel> getMemoListByTabId(Context context, Long id) {
        if (memoList == null) {
            memoList = new MutableLiveData<>();
            memoList.setValue(new HashMap<>());
        }

        if (memoList.getValue().get(id) == null) {
            memoList.getValue().put(id, new ArrayList<>());
        }
        List<MainMemoList> list = Database.getDatabase(context).mainMemoListDao().getAll(id);
        return getMainMemoList(context,list);
    }

    private ArrayList<MainMemoListModel> getMainMemoList(Context context, List<MainMemoList> list) {
        ArrayList<MainMemoListModel> arr = new ArrayList<>();

        for (MainMemoList item : list) {
            MainMemoListModel memoListModel = new MainMemoListModel();
            MemoItem memoItem = Database.getDatabase(context).mainMemoListDao().getLastMemoItem(item.memoRoom.id);
            String value = "";

            switch (MemoDataType.fromTypeCode(memoItem.valueCategory)) {
                case TEXT: {
                    //택스트 벨류 넣기
                    value = Database.getDatabase(context).mainMemoListDao().getTextValue(memoItem.id);
                    break;
                }
                case FILE: {
                    // 파일명 넣기
                    break;
                }
                case IMAGE: {
                    value = context.getString(R.string.file_img);
                    break;
                }
                case URL: {
                    value = context.getString(R.string.file_url);
                    break;
                    //url
                }
                case TODO: {
                    value = context.getString(R.string.file_todo);
                    value = value.replaceAll("*", "title"); /*todo The title must reflect the title of todoList here. */
                    memoListModel.setLastMemoItemValue(value);
                }
            }

            memoListModel.setLastMemoItemValue(value);
            memoListModel.setMemoListItem(item);
            memoListModel.setDate(memoItem.date);
            arr.add(memoListModel);
        }

        return arr;
    }
}
