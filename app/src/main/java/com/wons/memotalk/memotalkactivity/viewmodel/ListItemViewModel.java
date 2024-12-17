package com.wons.memotalk.memotalkactivity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.ListItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListItemViewModel extends AndroidViewModel {
    private Executor executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<ListItem> memoRoomData;

    public ListItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadData(long memoRoomId, long tabId) {
        executor.execute(() -> {
            memoRoomData = (MutableLiveData<ListItem>) Database.getDatabase(getApplication()).listItemDao().getListItemByRoomId(memoRoomId);

            if (memoRoomData.getValue() == null) {
                executor.execute(() -> {
                    String title = getApplication().getString(R.string.default_memo_name);
                    Long id = Database.getDatabase(getApplication()).listItemDao().getLastPk();
                    if (id != null) {
                        title += " " + id;
                    }
                    ListItem item = new ListItem();
                    item.title = title.trim();
                    item.listId = tabId;
                    item.fix = false;
                    //todo 나중에 기본 이름 추가 해야됨
                    item.iconName = null;
                    this.memoRoomData.setValue(item);
                });
            }
        });
    }

    public String getTitle() {
        if (this.memoRoomData.getValue() == null) {
            return "";
        }
        return this.memoRoomData.getValue().title;
    }
}
