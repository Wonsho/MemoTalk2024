package com.wons.memotalk.memotalkactivity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.ListItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListItemViewModel extends AndroidViewModel {
    private Executor executor = Executors.newSingleThreadExecutor();
    public LiveData<ListItem> memoRoomData;
    private MutableLiveData<Long> memoRoomId;
    private MutableLiveData<Long> tabId;

    public ListItemViewModel(@NonNull Application application) {
        super(application);
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
            memoRoomData = Database.getDatabase(getApplication()).listItemDao().getListItemByRoomId(memoRoomId);
        });
    }

    public String getTitle() {
        if (this.memoRoomData == null || this.memoRoomData.getValue() == null) {
            return getApplication().getString(R.string.default_memo_name);
        }
        return this.memoRoomData.getValue().title;
    }
}
