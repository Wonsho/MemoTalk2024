package com.wons.memotalk.mainactivity.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.MainFragment;

import java.util.ArrayList;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private ArrayList<Tab> tabs;

    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Tab> tabs) {
        super(fragmentManager, lifecycle);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new MainFragment(position);
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }
}
