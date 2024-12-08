package com.wons.memotalk.mainactivity.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ListItemListNameBinding;
import com.wons.memotalk.entity.Tab;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Long selectedTabId;
    private ArrayList<Tab> tabs = new ArrayList<>();

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public Object getItem(int i) {
        return tabs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItemListNameBinding binding;
        if (view == null) {
            binding = ListItemListNameBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        } else {
            binding = ListItemListNameBinding.bind(view);
        }
        Tab tab = tabs.get(i);
        String title = tabs.get(i).tabName;

        if (tab.id == this.selectedTabId) {
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new StrikethroughSpan(), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.value.setText(spannableString);
        } else {
            binding.value.setText(title);
        }
        return binding.getRoot();
    }

    public void setData(Long tabId, ArrayList<Tab> list ) {
        this.tabs = list;
        this.selectedTabId = tabId;
    }
}
