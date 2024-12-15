package com.wons.memotalk.mainactivity.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wons.memotalk.Database;
import com.wons.memotalk.dao.TabDao;
import com.wons.memotalk.entity.TabItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TabViewModel extends AndroidViewModel {
    private LiveData<List<TabItem>> list;
    Executor executor = Executors.newSingleThreadExecutor();

    public TabViewModel(@NonNull Application application) {
        super(application);
        TabDao dao = Database.getDatabase(application).tabDao();
        list = dao.getAll();
    }


    public LiveData<java.util.List<TabItem>> getTabs() {
        return this.list;
    }

    public void insert(TabItem tabItem) {
        executor.execute(() -> {
            Database.getDatabase(getApplication()).tabDao().insert(tabItem);
        });
    }

    public TabItem getByIndex(int index) {
        return this.list.getValue().get(index);
    }

    public void update(TabItem tabItem) {
        executor.execute(() -> {
            Database.getDatabase(getApplication()).tabDao().update(tabItem);
        });
    }
}
