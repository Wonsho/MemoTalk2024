package com.wons.memotalk.mainactivity.adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wons.memotalk.entity.TabItem;
import com.wons.memotalk.mainactivity.fragments.MemoListFragment;

import java.util.ArrayList;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public final static String KEY = "memoRoom";
    private java.util.List<TabItem> tabItems;

    public ViewPager2Adapter(FragmentActivity fragment) {
        super(fragment);
        this.tabItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new MemoListFragment();
        Bundle args = new Bundle();
        args.putLong(KEY, tabItems.get(position).id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabItems.size();
    }

    public void setData(java.util.List<TabItem> tabItems) {
        if (tabItems.size() == this.tabItems.size()) {
            this.tabItems = tabItems;
            notifyItemRangeChanged(0, this.tabItems.size());
            Log.i("ViewPager2Adapter", "same Size Data  : " + tabItems.size());
        } else {
            int dif = this.tabItems.size() - tabItems.size();

            if (dif < 0) {
                this.tabItems = tabItems;
                notifyItemInserted(this.tabItems.size());
                Log.i("ViewPager2Adapter", "add Data  : " + tabItems.size());
            } else {
                //todo 제거된 데이터
                Log.i("ViewPager2Adapter", "del Data  : " + tabItems.size());

            }
        }
    }
}
