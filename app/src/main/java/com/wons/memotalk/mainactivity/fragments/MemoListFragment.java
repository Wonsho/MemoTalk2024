package com.wons.memotalk.mainactivity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.FragmentMemoListBinding;
import com.wons.memotalk.key.KeyValues;
import com.wons.memotalk.mainactivity.adapter.MemoRoomAdapter;
import com.wons.memotalk.mainactivity.adapter.ViewPager2Adapter;
import com.wons.memotalk.memotalkactivity.MemoActivity;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class MemoListFragment extends Fragment {
    private long listId;
    private FragmentMemoListBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.binding == null) {
            this.binding = FragmentMemoListBinding.inflate(inflater, container, false);
        }
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        this.listId = args.getLong(ViewPager2Adapter.KEY);
        // todo listViewmodel set Observer
        setListView();
        setOnClickMenu();
    }

    private void setListView() {
        if (binding.lv.getAdapter() == null) {
            binding.lv.setAdapter(new MemoRoomAdapter());
        }
        //todo data notify
    }

    private void setOnClickMenu() {
        binding.btnMemoUtil.setOnClickListener((view) -> {
            PopupMenu popupMenu = new PopupMenu(getActivity(), binding.btnMemoUtil);
            popupMenu.getMenuInflater().inflate(R.menu.popup_memo_room, popupMenu.getMenu()); // 메뉴 리소스 설정

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.btn_add_memo_room) {
                        // todo intent memoRoot
                        Intent intent = new Intent(getActivity(), MemoActivity.class);
                        intent.putExtra(KeyValues.LIST_ID, listId);
                        intent.putExtra(KeyValues.MEMO_ROOM_ID, -1L);
                        startActivity(intent);
                    }

                    if (menuItem.getItemId() == R.id.btn_d_memo_room) {
                        // todo 보류
                    }

                    if (menuItem.getItemId() == R.id.btn_c_memo_room) {
                        //todo rename memoRoom
                    }
                    return false;
                }
            });
            popupMenu.show();
        });
    }

    private void setOnClickList() {
            //todo click list
    }

    private void setListONLongClick() {
        //todo click list long click
    }
}