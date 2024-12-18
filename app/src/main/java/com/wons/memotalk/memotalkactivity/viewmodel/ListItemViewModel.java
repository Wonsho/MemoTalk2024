package com.wons.memotalk.memotalkactivity.viewmodel;

import android.app.Application;

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
    private MutableLiveData<Long> memoRoomId;
    private MutableLiveData<Long> tabId;

    public ListItemViewModel(@NonNull Application application) {
        super(application);
        this.memoRoomData = new MutableLiveData<>();
        this.memoRoomId = new MutableLiveData<>();
        this.tabId = new MutableLiveData<>();

    }

    public void loadData(long memoRoomId, long tabId) {

        if (this.memoRoomId == null) {
            this.memoRoomId = new MutableLiveData<>();
            this.memoRoomId.setValue(memoRoomId);
        }

        if (this.tabId == null) {
            this.tabId = new MutableLiveData<>();
            this.tabId.setValue(tabId);
        }

        executor.execute(() -> {
            memoRoomData.postValue(Database.getDatabase(getApplication()).listItemDao().getListItemByRoomId(memoRoomId).getValue());
        });
    }

    public String getTitle() {
        if (this.memoRoomData == null || this.memoRoomData.getValue() == null) {
            return getApplication().getString(R.string.default_memo_name);
        }
        return this.memoRoomData.getValue().title;
    }

    public Long getMemoRoomId() {
        if (this.memoRoomId == null || this.memoRoomId.getValue() == null) {
            return -1L;
        }
        return this.memoRoomId.getValue();
    }

    public Long getListId() {
        return this.tabId.getValue();
    }

    public void insertMemoRoom() {
        executor.execute(() -> {
            ListItem listItem = new ListItem();
            Long id = Database.getDatabase(getApplication()).listItemDao().insert(listItem);
            ListItem listItem1 = Database.getDatabase(getApplication()).listItemDao().getListItemByRoomId(id).getValue();
            listItem1.title = getApplication().getString(R.string.default_memo_name) + " " + listItem1.roomId;
            listItem1.iconId = IconId.DEFAULT_ICON.getId();
            listItem1.fix = false;
            listItem1.listId = this.tabId.getValue();

        });
    }
}
