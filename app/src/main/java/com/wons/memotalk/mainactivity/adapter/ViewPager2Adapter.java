package com.wons.memotalk.mainactivity.adapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.fragments.MemoListFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private List<Tab> tabs;

    public ViewPager2Adapter(FragmentActivity fragment) {
        super(fragment);
        this.tabs = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new MemoListFragment();
        Bundle args = new Bundle();
        args.putLong("asd",tabs.get(position).id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }

    public void setData(List<Tab> tabs) {
        if (tabs.size() == this.tabs.size()) {
            //todo 동일한 개수의 아이템
            this.tabs = tabs;
            notifyItemRangeChanged(0, this.tabs.size());
            Log.i("ViewPager2Adapter", "same Size Data  : " + tabs.size());
        } else {
            int dif = this.tabs.size() - tabs.size();

            if (dif < 0) {
                this.tabs = tabs;
                notifyItemInserted(this.tabs.size());
                Log.i("ViewPager2Adapter", "add Data  : " + tabs.size());
                //todo 추가된 데이터
            } else {
                //todo 제거된 데이터
                Log.i("ViewPager2Adapter", "del Data  : " + tabs.size());

            }
            //todo 다르다면 작냐 크냐
        }
    }
}
