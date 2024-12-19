package com.wons.memotalk.memotalkactivity.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.ItemSnapshotList;

import com.wons.memotalk.Database;
import com.wons.memotalk.entity.IconId;
import com.wons.memotalk.entity.ListItem;

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

    public void setMemoRoomId(Long id) {
        this.memoRoomId.postValue(id);
    }

    public void setTabId(Long id) {
        this.tabId.postValue(id);
    }

    public String getTitle() {
        if (listItemViewModelLiveData.getValue() == null) {
            return null;
        } else {
            return listItemViewModelLiveData.getValue().title;
        }
    }

    public void update(ListItem listItem, Context context) {
        executor.execute(() -> {
            Database.getDatabase(context).listItemDao().update(listItem);
            this.listItemViewModelLiveData.postValue(listItem);
        });
    }

    public boolean isFist() {
        if (listItemViewModelLiveData.getValue() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insert(ListItem listItem, Context context) {
        listItem.title = null;
        listItem.fix = false;
        listItem.listId = this.tabId.getValue();
        listItem.iconId = IconId.DEFAULT_ICON.getId();
        executor.execute(() -> {
            Long id = Database.getDatabase(context).listItemDao().insert(listItem);
            memoRoomId.postValue(id);
        });
    }

    public void loadListItem(Context context, Long id) {
        executor.execute(() -> {
            ListItem item = Database.getDatabase(context).listItemDao().getListItemByRoomId(id);
            this.listItemViewModelLiveData.postValue(item);
        });
    }
}

