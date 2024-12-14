package com.wons.memotalk.mainactivity.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.fragments.BlankFragment;

import java.util.List;

public class DemoCollectionAdapter extends FragmentStateAdapter {
    private List<Tab> tabs;

    public DemoCollectionAdapter(FragmentActivity fragment, List<Tab> tabs) {
        super(fragment);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt("asd", position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }
}
