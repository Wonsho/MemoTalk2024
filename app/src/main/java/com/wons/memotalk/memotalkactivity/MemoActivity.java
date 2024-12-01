package com.wons.memotalk.memotalkactivity;

import android.os.Bundle;
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

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMemoBinding;
import com.wons.memotalk.entity.MemoRoom;
import com.wons.memotalk.mainactivity.MainFragment;
import com.wons.memotalk.memotalkactivity.viewmodel.MemoItemViewModel;
import com.wons.memotalk.memotalkactivity.viewmodel.MemoViewModel;

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
            getDataFromIntent();
        }

        if (this.memoItemViewModel == null) {
            memoItemViewModel = new ViewModelProvider(this).get(MemoItemViewModel.class);
        }
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

    private void setView() {
        String title = this.memoViewModel.getMemoTitle();
        Log.i("MemoActivity", "memo Title is " + title);
        binding.tvTitle.setText(title.trim());
    }

    private void setListView() {
        if (binding.lvMemo.getAdapter() == null) {
            // adapter is null
        }
    }

    private void setOnClick() {

        binding.btnSend.setOnClickListener((view) -> {
            if (!binding.etText.getText().toString().trim().isEmpty()) {
                //전송
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