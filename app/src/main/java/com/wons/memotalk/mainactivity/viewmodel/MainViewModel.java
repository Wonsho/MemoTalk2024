package com.wons.memotalk.mainactivity.viewmodel;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Transaction;

import com.wons.memotalk.Database;
import com.wons.memotalk.MainDatabase;
import com.wons.memotalk.R;
import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.livedata.AddTabState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainViewModel extends ViewModel {
    private MutableLiveData<AddTabState> tabUiState;
    private MutableLiveData<ArrayList<Tab>> tabArrData;
    private MainDatabase db;

    public long getLastTabId() {
        ArrayList<Tab> arr = tabArrData.getValue();

        long max = 0L;

        for (Tab t : arr) {
            if (max < t.id) {
                max = (long) t.id;
            }
        }
        return max;
    }

    public boolean getTabUiState() {
        if (tabUiState == null) {
            AddTabState ts = new AddTabState();
            ts.show = false;
            tabUiState = new MutableLiveData<>();
            tabUiState.setValue(ts);
        }
        return Objects.requireNonNull(tabUiState.getValue()).show;
    }

    public void setTabUiState(boolean b) {
        Objects.requireNonNull(this.tabUiState.getValue()).show = b;
    }

    public void insertTabData(Tab tab) {
        db.tabDao().insert(tab);
    }


    public void getDataFromDatabase(Context context) {

        MemoItem memoItem = new MemoItem();
        memoItem.check = true;
        memoItem.valueCategory = 5;

        if (this.db == null) {
            this.db = Database.getDatabase(context);
        }
        if (tabArrData == null) {
            tabArrData = new MutableLiveData<>();
        }

        ArrayList<Tab> arr;
        arr = (ArrayList<Tab>) db.tabDao().getAll();

        if (arr.isEmpty()) {
            Tab t = new Tab();
            t.tabName = context.getString(R.string.tab) + "1";
            db.tabDao().insert(t);
        }
        arr = (ArrayList<Tab>) db.tabDao().getAll();

        this.tabArrData.setValue(arr);
    }

    public ArrayList<Tab> getTabList() {
        return this.tabArrData.getValue();
    }

    public void changeTabName(Context context, Long id, String title) {
        ArrayList<Tab> arr = this.tabArrData.getValue();

        for (Tab tab : arr) {
            if (tab.id == id) {
                //todo 현재 탭변경
                tab.tabName = title;
                Database.getDatabase(context).tabDao().update(tab);
            }
        }
    }

    @Transaction
    public void deleteTab(Context context, Tab tab) {

    }
}
