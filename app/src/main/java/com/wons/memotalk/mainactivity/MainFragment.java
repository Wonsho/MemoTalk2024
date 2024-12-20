package com.wons.memotalk.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.paging.LimitOffsetPagingSource;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.FragmentMainBinding;
import com.wons.memotalk.entity.memoList.MainMemoListModel;
import com.wons.memotalk.mainactivity.adapter.MainMemoListAdapter;
import com.wons.memotalk.memotalkactivity.MemoActivity;

import java.util.ArrayList;

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

    public void addList() {
        Intent intent = new Intent(getActivity(), MemoActivity.class);
        intent.putExtra(FRAGMENTS_ID, this.id);
        intent.putExtra(MEMO_ID, -1L);
        startActivity(intent);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setListView();
        setOnClick();

    }

    private void setListView() {
        if (binding.lv.getAdapter() == null) {
            binding.lv.setAdapter(new MainMemoListAdapter());
        }
        ArrayList<MainMemoListModel> models = ((MainActivity) getActivity()).getListModel(this.id);
        ((MainMemoListAdapter) binding.lv.getAdapter()).setList(models);
//        ((MainMemoListAdapter) binding.lv.getAdapter()).notifyDataSetChanged();
        // 메인액티비티 에서 데이터 가져옴
    }

    private void setOnClick() {

        binding.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Long memoRoomId = ((MainActivity) getActivity()).getListModel(id).get(i).getMemoListItem().memoRoom.id;
                Long tabId = ((MainActivity) getActivity()).getListModel(id).get(i).getMemoListItem().memoRoom.tabId;
                Intent intent = new Intent(getActivity(), MemoActivity.class);
                intent.putExtra(FRAGMENTS_ID, tabId);
                intent.putExtra(MEMO_ID, memoRoomId);
                startActivity(intent);

                //todo 해당 리스트로 이동
            }
        });

        binding.lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.memo_list_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.sort_cut) {
                            //todo 바로가기
                        }
                        if (menuItem.getItemId() == R.id.memo_rename) {
                            //todo 이름 바꾸기
                        }
                        if (menuItem.getItemId() == R.id.mmemo_delete) {
                            //todo 삭제
                        }
                        if (menuItem.getItemId() == R.id.fix_top ) {
                            // todo 상단 고정
                        }
                        if (menuItem.getItemId() == R.id.memo_lock) {
                            //todo 메모 락
                        }
                        if (menuItem.getItemId() == R.id.switch_tab) {
                            //todo 위치 변경
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }
}