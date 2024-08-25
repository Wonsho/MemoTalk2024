package com.wons.memotalk.mainactivity.service;

import android.content.Context;

import com.wons.memotalk.DataBase;
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.repository.TabRepository;
import java.util.List;

public class MainService {
    private List<Tab> tabList;
    private final TabRepository repository;

    public MainService(Context context) {
        this.repository = DataBase.loadTabRepository(context);
    }

    public Tab getTabByPosition(int position) {
        return tabList.get(position);
    }


    public List<Tab> getAllTab() {
        tabList = repository.getAll();

        if (tabList.isEmpty()) {
            Tab tab = new Tab();
            tab.tabName = "Main";
            this.tabList.add(tab);
            repository.insertTab(tab);
        }
        tabList = repository.getAll();
        return this.tabList;
    }

    public int getTabSize() {
        return this.tabList.size();
    }

    public long getLastTabId() {
        long max = 0;

        for (Tab tab : this.tabList) {
            long n = tab.id;
            max = Math.max(max, n);
        }
        return max+1;
    }

    public void insertTabData(String name) {
        Tab tab = new Tab();
        tab.tabName = name;
        repository.insertTab(tab);
        tab.id = getLastTabId();
        tabList.add(tab);
    }
}
