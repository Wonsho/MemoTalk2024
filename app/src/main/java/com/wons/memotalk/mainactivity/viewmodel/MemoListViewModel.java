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
import com.wons.memotalk.memotalkactivity.viewmodel.MemoItemViewModel;
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MemoListViewModel extends ViewModel {
    private MutableLiveData<HashMap<Long, ArrayList<MainMemoListModel>>> memoList;

    public void moveToList(Long oldId, Long newId, int memoIndex, Context context) {
        MainMemoListModel model = memoList.getValue().get(oldId).get(memoIndex);
        model.getMemoListItem().memoRoom.tabId = newId;
        memoList.getValue().get(newId).add(model);
        memoList.getValue().get(oldId).remove(memoIndex);
        Database.getDatabase(context).mainMemoListDao().update(model.getMemoListItem().memoRoom);
        sortByTime(memoList.getValue().get(oldId));
        sortByFix(memoList.getValue().get(oldId));
        sortByTime(memoList.getValue().get(newId));
        sortByFix(memoList.getValue().get(newId));
    }

    public void changeMemoRoomName(Context context, int index, Long fragmentsId, String name) {
        memoList.getValue().get(fragmentsId).get(index).getMemoListItem().memoRoom.title = name;
        Database.getDatabase(context).mainMemoListDao().update(
                memoList.getValue().get(fragmentsId).get(index).getMemoListItem().memoRoom
        );
    }

    public String getMemoRoomTitle(int index, Long fragmentsId) {
        return this.memoList.getValue().get(fragmentsId).get(index).getMemoListItem().memoRoom.title;
    }

    public void itemToFix(Context context, Long fragmentsId, int index) {
        memoList.getValue().get(fragmentsId).get(index).getMemoListItem().memoRoom.fixed =
                !memoList.getValue().get(fragmentsId).get(index).getMemoListItem().memoRoom.fixed;
        Database.getDatabase(context).mainMemoListDao().update(
                memoList.getValue().get(fragmentsId).get(index).getMemoListItem().memoRoom
        );
        sortByTime(
                memoList.getValue().get(fragmentsId)
        );
        sortByFix(memoList.getValue().get(fragmentsId));
    }


    public ArrayList<MainMemoListModel> getMemoListByTabId(Context context, Long id) {
        List<MainMemoList> list = Database.getDatabase(context).mainMemoListDao().getAll(id);

        if (memoList == null) {
            memoList = new MutableLiveData<>();
            memoList.setValue(new HashMap<>());
        }

        if (memoList.getValue().get(id) == null) {
            memoList.getValue().put(id, new ArrayList<>());
        }
        //-- 정렬
        this.memoList.getValue().put(id, mapperMainMemoListToModel(context, list));

        sortByTime(this.memoList.getValue().get(id));
        sortByFix(this.memoList.getValue().get(id));

        return this.memoList.getValue().get(id);
    }

    private void sortById(ArrayList<MainMemoListModel> list) {
        Collections.sort(list, new Comparator<MainMemoListModel>() {
            @Override
            public int compare(MainMemoListModel mainMemoList, MainMemoListModel t1) {
                return Long.compare(mainMemoList.getMemoListItem().memoRoom.id, t1.getMemoListItem().memoRoom.id);
            }
        });
    }

    private void sortByFix(ArrayList<MainMemoListModel> list) {
        Collections.sort(list, new Comparator<MainMemoListModel>() {
            @Override
            public int compare(MainMemoListModel mainMemoList, MainMemoListModel t1) {
                return Boolean.compare(t1.getMemoListItem().memoRoom.fixed, mainMemoList.getMemoListItem().memoRoom.fixed);
            }
        });
    }

    private void sortByTime(ArrayList<MainMemoListModel> list) {
        Collections.sort(list, new Comparator<MainMemoListModel>() {
            @Override
            public int compare(MainMemoListModel mainMemoList, MainMemoListModel t1) {
                return Long.compare(t1.getDate(), mainMemoList.getDate());
            }
        });
    }

    private ArrayList<MainMemoListModel> mapperMainMemoListToModel(Context context, List<MainMemoList> list) {
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
                    value = value.replaceAll("-", "title"); /*todo The title must reflect the title of todoList here. */
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
