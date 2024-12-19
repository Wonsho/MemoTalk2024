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

import javax.crypto.KeyAgreement;

public class MemoActivity extends AppCompatActivity {
    private ActivityMemoBinding binding;
    private ListItemViewModel listItemViewModel;

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
            listItemViewModel.memoRoomId.observe(this, id -> {
                Toast.makeText(getApplication(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                listItemViewModel.loadListItem(this, id);
                //todo 새로운값 들어옴
            });

            listItemViewModel.listItemViewModelLiveData.observe(this, item -> {
                if (item == null) {
                    return;
                }

                if (item.title == null) {
                    //todo update
                    item.title = getString(R.string.default_memo_name) + " " + item.roomId;
                    Toast.makeText(this, "title is null" , Toast.LENGTH_SHORT).show();
                    listItemViewModel.update(item, this);
                    return;
                }
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show();
                setTitle();
            });

            long memoRoomId = getIntent().getLongExtra(KeyValues.MEMO_ROOM_ID, -1L);
            long tabId = getIntent().getLongExtra(KeyValues.LIST_ID, -1L);
            listItemViewModel.setTabId(tabId);
            listItemViewModel.setMemoRoomId(memoRoomId);
        }

        setOnClickSend();
    }

    private void setTitle() {
        String title = listItemViewModel.getTitle();

        if (title == null) {
            title = getString(R.string.default_memo_name);
        }
        binding.tvTitle.setText(title);
    }

    private void setOnClickSend() {
        binding.btnSend.setOnClickListener((view) -> {
            String value = binding.etText.getText().toString().trim();

            if (!value.isEmpty()) {
                if (listItemViewModel.isFist()) {
                    //todo 첫데이터
                    listItemViewModel.insert(new ListItem(), this);
                } else {
                    //todo 첫데이터 아님
                }
            }
            binding.etText.setText("");
        });
    }
}