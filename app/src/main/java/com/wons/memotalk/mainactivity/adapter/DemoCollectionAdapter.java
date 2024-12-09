package com.wons.memotalk.mainactivity.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wons.memotalk.mainactivity.fragments.MainFragment;

public class DemoCollectionAdapter extends FragmentStateAdapter {
    public DemoCollectionAdapter(FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new MainFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(MainFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
