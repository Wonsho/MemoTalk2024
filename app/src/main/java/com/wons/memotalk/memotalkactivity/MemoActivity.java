package com.wons.memotalk.memotalkactivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMemoBinding;
import com.wons.memotalk.entity.ListItem;
import com.wons.memotalk.key.KeyValues;
import com.wons.memotalk.memotalkactivity.viewmodel.ListItemViewModel;
import com.wons.memotalk.memotalkactivity.viewmodel.MemoDataViewModel;

public class MemoActivity extends AppCompatActivity {
    private ActivityMemoBinding binding;
    private ListItemViewModel listItemViewModel;
    private MemoDataViewModel memoDataViewModel;


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
            listItemViewModel.listItemViewModelLiveData.observe(this, item -> {
                //todo setTitle, and load MemoData
                setTitle();
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show();

            });

            listItemViewModel.memoRoomId.observe(this, id -> {
                //todo 해당 데이터 로드
                // if == -1 이면 insertMemo
                if (id == -1L) {
                    listItemViewModel.insertMemoRoom(this);
                } else {
                    //로드 리스트 아이템뷰 모델
                    listItemViewModel.loadMemoRoom(this, id);
                }

            });

            long roomId = getIntent().getLongExtra(KeyValues.MEMO_ROOM_ID, -1L);
            long tabId = getIntent().getLongExtra(KeyValues.LIST_ID, -1L);
            listItemViewModel.setMemoRoomId(roomId);
            listItemViewModel.setTabId(tabId);
        }
    }

    private void setTitle() {
        binding.tvTitle.setText(listItemViewModel.getTitle());
    }
}