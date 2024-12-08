package com.wons.memotalk.change_list_name_activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityChangeListNameBinding;
import com.wons.memotalk.mainactivity.adapter.MainMemoListAdapter;
import com.wons.memotalk.memotalkactivity.adapter.MemoListAdapter;
import com.wons.memotalk.util.IntentKey;
import com.wons.memotalk.util.SystemUtil;

public class ChangeListNameActivity extends AppCompatActivity {
    private ActivityChangeListNameBinding binding;
    private ChangeListNameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        if (this.binding == null) {
            binding = ActivityChangeListNameBinding.inflate(getLayoutInflater());
        }
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        if (this.viewModel == null) {
            viewModel = new ViewModelProvider(this).get(ChangeListNameViewModel.class);
        }

        setIntentData(getIntent());
        setView();
        setListView();
        setOnClick();
    }

    private void setIntentData(Intent intent) {
        String listName = intent.getStringExtra(IntentKey.LIST_NAME);
        Long listId = intent.getLongExtra(IntentKey.FRAGMENTS_ID, -1L);
        viewModel.setListData(listName, listId);
    }

    private void setView() {
        String info = binding.tvInfo.getText().toString().trim();
        info = info.replaceAll("-" , viewModel.getTitle());
        binding.tvInfo.setText(info);
    }

    private void setListView() {
        if (binding.lvMemo.getAdapter() == null) {
            binding.lvMemo.setAdapter(new MainMemoListAdapter());
        }

    }

    private void setOnClick() {
        binding.btnBack.setOnClickListener((view) -> {
            if (SystemUtil.checkExit()) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.go_back), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvMemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //todo show dialog
                Toast.makeText(getApplicationContext(), "Rename", Toast.LENGTH_SHORT).show();
            }
        });
    }
}