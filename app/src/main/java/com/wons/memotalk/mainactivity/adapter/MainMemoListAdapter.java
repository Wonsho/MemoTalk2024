package com.wons.memotalk.mainactivity.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ListMemoMainBinding;
import com.wons.memotalk.entity.memoList.MainMemoListModel;
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;

public class MainMemoListAdapter extends BaseAdapter {
    private ArrayList<MainMemoListModel> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ListMemoMainBinding.inflate(LayoutInflater.from(viewGroup.getContext())).getRoot();
        }
        MainMemoListModel model = this.list.get(i);
        ListMemoMainBinding binding = ListMemoMainBinding.bind(view);
        binding.img.setImageResource(model.getMemoListItem().listIcon.iconId);
        binding.tvTime.setText(DateUtil.dateFormat(binding.getRoot().getContext().getString(R.string.memo_list_time_format),
                model.getDate()));
        binding.tvSubTitle.setText(model.getMemoListItem().memoRoom.title);
        binding.tvDataInfo.setText(model.getLastMemoItemValue());

        if (model.getMemoListItem().memoRoom.fixed) {
            binding.fix.setVisibility(View.VISIBLE);
        } else {
            binding.fix.setVisibility(View.GONE);
        }
        return binding.getRoot();
    }

    public void setList(ArrayList<MainMemoListModel> list) {
        this.list = list;
    }

}
