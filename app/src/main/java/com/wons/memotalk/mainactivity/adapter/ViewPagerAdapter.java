package com.wons.memotalk.mainactivity.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.MainFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private ArrayList<Tab> items;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public Tab getTabById(int id) {
        return this.items.get(id);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("createFragment", "pass : " + position);

        return new MainFragment(items.get(position).id);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItem(ArrayList<Tab> items) {
        this.items = items;
    }
}
