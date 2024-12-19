package com.wons.memotalk.memotalkactivity.viewmodel;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.callback.CallBack;
import com.wons.memotalk.entity.IconId;
import com.wons.memotalk.entity.ListItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListItemViewModel extends AndroidViewModel {
    private Executor executor = Executors.newSingleThreadExecutor();
    public MutableLiveData<ListItem> memoRoomData;
    public MutableLiveData<Long> memoRoomId;
    public MutableLiveData<Long> tabId;

    public ListItemViewModel(@NonNull Application application) {
        super(application);
        this.memoRoomData = new MutableLiveData<>();
        this.memoRoomId = new MutableLiveData<>();
        this.tabId = new MutableLiveData<>();
    }

    public String getTitle() {
        if (this.memoRoomData.getValue() == null) {
            return getApplication().getString(R.string.default_memo_name);
        } else {
            return this.memoRoomData.getValue().title;
        }
    }

    public void setRoomId(long id) {
        this.memoRoomId.setValue(id);
    }

    public void setTabId(long id) {
        this.tabId.setValue(id);
    }

    public void insertMemoRoom() {
        executor.execute(() -> {
            ListItem item = new ListItem();
            this.memoRoomId.postValue(Database.getDatabase(getApplication()).listItemDao().insert(item));
        });
    }

    public void loadMemoRoom(long id) {
        executor.execute(() -> {
            ListItem item = Database.getDatabase(getApplication()).listItemDao().getListItemByRoomId(id);
            if (item.title == null || item.title.isEmpty()) {
                item.title = getApplication().getString(R.string.default_memo_name) + " " + id;
                item.fix = false;
                item.iconId = IconId.DEFAULT_ICON.getId();
                item.listId = this.tabId.getValue();
                Database.getDatabase(getApplication()).listItemDao().update(item);
            }
            this.memoRoomData.postValue(Database.getDatabase(getApplication()).listItemDao().getListItemByRoomId(id));
        });
    }

    public long getMemoRoomId() {
        if (this.memoRoomId == null || this.memoRoomId.getValue() == null) {
            return -1L;
        } else {
            return this.memoRoomId.getValue();
        }
    }
}
