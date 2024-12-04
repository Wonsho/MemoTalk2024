package com.wons.memotalk.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
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
import com.wons.memotalk.mainactivity.adapter.MainMemoListAdapter;
import com.wons.memotalk.memotalkactivity.MemoActivity;

public class MainFragment extends Fragment {
    private long id;
    private FragmentMainBinding binding;
    public static final String FRAGMENTS_ID = "fragmentsId";
    public static final String MEMO_ID = "memoId";

    public MainFragment(long position) {
        this.id = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    //메모 추가 눌렀을경우
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setListView();

        binding.btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 리스트 추가 -> 메모방으로 바로 넘겨주고난다음 데이터가 들어갈시 데이터 베이스에 저장
                Intent intent = new Intent(getActivity(), MemoActivity.class);
                intent.putExtra(FRAGMENTS_ID, id);
                intent.putExtra(MEMO_ID, -1L);
                startActivity(intent);
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

    private void setListView() {
        if (binding.lv.getAdapter() == null) {
            binding.lv.setAdapter(new MainMemoListAdapter());
        }
        // 메인액티비티 에서 데이터 가져옴
    }
}