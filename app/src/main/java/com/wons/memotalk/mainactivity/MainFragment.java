package com.wons.memotalk.mainactivity;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private long id;
    private FragmentMainBinding binding;

    public MainFragment(long position) {
        this.id = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 리스트 추가
            }
        });

        binding.btnDeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), R.style.BasePopupMenu);
                PopupMenu menu = new PopupMenu(wrapper, view);
                getActivity().getMenuInflater().inflate(R.menu.popup, menu.getMenu());

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.btn_d_list) {
                            Toast.makeText(getContext(), "LIST", Toast.LENGTH_SHORT).show();
                            //todo 편집 창 이동
                        } else {
                            Toast.makeText(getContext(), "TAB", Toast.LENGTH_SHORT).show();
                            //todo 안내 다이로그 띄우기 (현재 탭의 모든 데이터가 삭제됩니다.)
                        }
                        return false;
                    }
                });
                menu.show();
            }
        });
    }
}