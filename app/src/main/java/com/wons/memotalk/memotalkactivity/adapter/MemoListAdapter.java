package com.wons.memotalk.memotalkactivity.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wons.memotalk.entity.MemoItem;

import java.util.ArrayList;
import java.util.List;

public class MemoListAdapter extends BaseAdapter {
    private ArrayList<MemoItem> memoItems;

    @Override
    public int getCount() {
        return memoItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public void setMemoItems(List<MemoItem> items) {
        this.memoItems = (ArrayList<MemoItem>) items;
    }
}
