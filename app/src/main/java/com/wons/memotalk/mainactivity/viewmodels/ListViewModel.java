package com.wons.memotalk.mainactivity.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wons.memotalk.entity.TabItem;

import java.util.HashMap;
import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private LiveData<HashMap<Long, List<TabItem>>> data;


    public ListViewModel(@NonNull Application application) {
        super(application);

    }
}
