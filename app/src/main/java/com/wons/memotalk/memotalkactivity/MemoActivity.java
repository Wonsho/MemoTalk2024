package com.wons.memotalk.memotalkactivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMemoBinding;
import com.wons.memotalk.entity.MemoItem;
import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.MemoDataType;
import com.wons.memotalk.entity.memo_data.MemoText;
import com.wons.memotalk.mainactivity.MainFragment;
import com.wons.memotalk.memotalkactivity.adapter.MemoListAdapter;
import com.wons.memotalk.memotalkactivity.viewmodel.MemoItemViewModel;
import com.wons.memotalk.memotalkactivity.viewmodel.MemoViewModel;
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;

public class MemoActivity extends AppCompatActivity {
    private MemoViewModel memoViewModel;
    private ActivityMemoBinding binding;
    private MemoItemViewModel memoItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (this.memoViewModel == null) {
            memoViewModel = new ViewModelProvider(this).get(MemoViewModel.class);
        }

        if (this.memoItemViewModel == null) {
            memoItemViewModel = new ViewModelProvider(this).get(MemoItemViewModel.class);
            getDataFromIntent();
        }
        setListView();
        setOnClick();
    }

    private void getDataFromIntent() {
        long fragmentsId = getIntent().getLongExtra(MainFragment.FRAGMENTS_ID, -1L);
        long memoId = getIntent().getLongExtra(MainFragment.MEMO_ID, -1L);
        setMemoData(fragmentsId, memoId);
    }

    private void setMemoData(Long fragmentsId, Long memoId) {
        this.memoViewModel.setMemoDataFromDataBase(this, fragmentsId, memoId);
        setView();
    }


    //todo 타이틀 is null
    private void setView() {
        String title = this.memoViewModel.getMemoTitle();
        Log.i("MemoActivity", "memo Title is " + title);
        binding.tvTitle.setText(title.trim());
    }


    private void setListView() {
        if (binding.lvMemo.getAdapter() == null) {
            binding.lvMemo.setAdapter(new MemoListAdapter());
            binding.lvMemo.setLayoutManager(new LinearLayoutManager(this));
        }
        MemoListAdapter adapter = ((MemoListAdapter) binding.lvMemo.getAdapter());
        //todo 이부분 adapter 에 아이템 추가
        adapter.setItemList(memoItemViewModel.getMemoData(this, memoViewModel.getMemoRoomId()));
        adapter.notifyItemRangeInserted(0, databaseList().length);
    }

    private void addDataToListView() {
        //todo 리스트 뷰에 insert data\
        binding.lvMemo.getAdapter().notifyDataSetChanged();
    }

    private void deleteDataToListView() {
        //todo 리스트 뷰에 delete Data
    }


    private void setOnClick() {

        binding.btnSend.setOnClickListener((view) -> {
            if (!binding.etText.getText().toString().trim().isEmpty()) {
                if (this.memoViewModel.getMemoRoomId() == -1L) {
                    //데이터가 처음일경우
                    //todo 메모룸 저장한다음 아이디값을 받아온다음
                    // 메모아이템 뷰모델에 전달
                    this.memoViewModel.saveMemoRoom(this);
                }

                this.memoItemViewModel.saveTextMemo(this, binding.etText.getText().toString().trim(), memoViewModel.getMemoRoomId());
                addDataToListView();
                binding.etText.setText("");
            }
        });

        //editText 누를때 유틸 뷰 사라지게 만듦
        binding.etText.setOnClickListener((view) -> {
            binding.btnUtil.setImageResource(R.drawable.plus);
            binding.layUtil.setVisibility(View.GONE);
        });

        //util 버튼 누를때 숨기고 보여주게 만듦
        binding.btnUtil.setOnClickListener((view) -> {
            try {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
            }

            if (binding.layUtil.getVisibility() == View.VISIBLE) {
                binding.btnUtil.setImageResource(R.drawable.plus);
                binding.layUtil.setVisibility(View.GONE);
            } else {
                binding.btnUtil.setImageResource(R.drawable.mul);
                binding.layUtil.setVisibility(View.VISIBLE);
            }
        });
    }
}