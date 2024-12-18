package com.wons.memotalk.memotalkactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wons.memotalk.databinding.ActivityMemoBinding;
import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.Text;
import com.wons.memotalk.key.KeyValues;
import com.wons.memotalk.memotalkactivity.adapter.MemoListAdapter;
import com.wons.memotalk.memotalkactivity.viewmodel.ListItemViewModel;
import com.wons.memotalk.memotalkactivity.viewmodel.MemoRoomItemViewModel;

public class MemoActivity extends AppCompatActivity {
    private ActivityMemoBinding binding;
    private ListItemViewModel listItemViewModel;
    private MemoRoomItemViewModel memoRoomItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        if (binding == null) {
            binding = ActivityMemoBinding.inflate(getLayoutInflater());
        }
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (listItemViewModel == null) {
            listItemViewModel = new ViewModelProvider(this).get(ListItemViewModel.class);
            listItemViewModel.loadData(getIntent().getLongExtra(KeyValues.MEMO_ROOM_ID, -1L), getIntent().getLongExtra(KeyValues.LIST_ID, -1L));
            listItemViewModel.memoRoomData.observe(this, listItem -> {
                setTitle();
            });
        }

        if (memoRoomItemViewModel == null) {
            memoRoomItemViewModel = new ViewModelProvider(this).get(MemoRoomItemViewModel.class);
        }
        setTitle();
        setOnClickBack();
        setView();
        setOnClickSend();
    }

    private void setOnClickBack() {
        //todo back 버튼 눌렀을 경우 3초 알림 띄우기
        binding.btnBack.setOnClickListener((view) -> {
            finish();
            //todo 나중에 수정
        });
    }

    private void setTitle() {
        binding.tvTitle.setText(listItemViewModel.getTitle());
        //todo 타이틀 지정
    }

    private void setView() {
        if (binding.lvMemo.getAdapter() == null) {
            binding.lvMemo.setAdapter(new MemoListAdapter());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.lvMemo.setLayoutManager(linearLayoutManager);
        }
        //todo 뷰 지정
    }

    private void updateView() {

    }

    private void setOnClickSearch() {
        //todo 검색 버튼 눌렀을경우 택스트 검색 가능
    }

    private void setOnClickMenu() {
        //todo 메뉴 버튼 눌렀을 경우
    }

    private void setOnClickUtils() {
        //todo util 버튼 눌렀을 경우
    }
    private void setOnClickSend() {
        //todo 보내기 버튼 눌렀을 경우
        String value = binding.etText.getText().toString().trim();

        if (!value.isEmpty()) {
            memoRoomItemViewModel.insertText(value);
            binding.etText.setText("");
        }
    }
}