package com.wons.memotalk.mainactivity.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wons.memotalk.Database;
import com.wons.memotalk.R;
import com.wons.memotalk.dao.TabDao;
import com.wons.memotalk.entity.Tab;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TabViewModel extends AndroidViewModel {
    private LiveData<List<Tab>> list;
    Executor executor = Executors.newSingleThreadExecutor();

    public TabViewModel(@NonNull Application application) {
        super(application);
    }


    private void getDataFromDataBase() {
        TabDao dao = Database.getDatabase(getApplication()).tabDao();
        list = dao.getAll();
    }

    public LiveData<List<Tab>> getTabs() {
        if (list == null) {
            getDataFromDataBase();
        }
        return this.list;
    }

    private void insert(Tab tab) {
        executor.execute(() -> {
            Database.getDatabase(getApplication()).tabDao().insert(tab);
        });
    }
}
