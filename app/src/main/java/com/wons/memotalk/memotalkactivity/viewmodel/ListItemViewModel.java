package com.wons.memotalk.memotalkactivity.viewmodel;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Transaction;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.IconId;
import com.wons.memotalk.entity.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListItemViewModel extends ViewModel {
    private final Executor executor = Executors.newSingleThreadExecutor();
    public MutableLiveData<ListItem> listItemViewModelLiveData;
    public MutableLiveData<Long> memoRoomId;
    public MutableLiveData<Long> tabId;

    ListItemViewModel() {
        this.listItemViewModelLiveData = new MutableLiveData<>();
        this.memoRoomId = new MutableLiveData<>();
        this.tabId = new MutableLiveData<>();
    }

    public String getTitle() {
        return this.listItemViewModelLiveData.getValue().title;
    }

    public void setMemoRoomId(Long id) {
        this.memoRoomId.postValue(id);
    }

    public void setTabId(Long id) {
        this.tabId.postValue(id);
    }

    @Transaction
    public void insertMemoRoom(Context context) {
        executor.execute(() -> {
            ListItem item = new ListItem();
            item.iconId = IconId.DEFAULT_ICON.getId();
            item.fix = false;
            item.listId = tabId.getValue();
            List<Integer> items = Database.getDatabase(context).listItemDao().getMemosWithKeyword(context.getString(R.string.default_memo_name));
            int index = -1;

            for (int i = 1; i < 100; i++) {
                if (!items.contains(i)) {
                    index = i;
                    break;
                }
            }

            item.title = context.getString(R.string.default_memo_name) + " " + index;
            Long id = Database.getDatabase(context).listItemDao().insert(item);
            this.memoRoomId.postValue(id);
        });
    }

    public void loadMemoRoom(Context context, long id) {
        executor.execute(() -> {
            ListItem item = Database.getDatabase(context).listItemDao().getListItemByRoomId(id);
            this.listItemViewModelLiveData.postValue(item);
        });
    }
}

