package com.wons.memotalk.mainactivity.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wons.memotalk.entity.ListInfo;

import java.util.List;

public class ListViewModel extends ViewModel {
    private LiveData<List<ListInfo>> lists;
}
