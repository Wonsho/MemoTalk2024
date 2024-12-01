package com.wons.memotalk.mainactivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMainBinding;
import com.wons.memotalk.databinding.DialogAddTitleBinding;
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.adapter.ViewPagerAdapter;
import com.wons.memotalk.mainactivity.viewmodel.MainViewModel;
import com.wons.memotalk.mainactivity.viewmodel.MemoListViewModel;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private MemoListViewModel memoListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (mainViewModel == null) {
            mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
            mainViewModel.getDataFromDatabase(this);
        }

        if (memoListViewModel == null) {
            memoListViewModel = new ViewModelProvider(this).get(MemoListViewModel.class);
        }
        notifyViewPagerView();
        notifyTabView();
        setOnClick();
        if (mainViewModel.getTabUiState()) {
            showDialog();
        }

    }

    private void setOnClick() {
        binding.btnAddTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog dialog;
        mainViewModel.setTabUiState(true);
        DialogAddTitleBinding binding1 = DialogAddTitleBinding.inflate(getLayoutInflater());
        StringBuilder sb = new StringBuilder();

        sb.append(getApplicationContext().getString(R.string.tab));
        sb.append(mainViewModel.getLastTabId() + 1);
        binding1.etText.setHint(sb.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(binding1.getRoot())
                .setCancelable(false);
        dialog =  builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        binding1.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.setTabUiState(false);
                dialog.dismiss();
            }
        });
        binding1.btnAddTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.setTabUiState(false);
                String tName = binding1.etText.getText().toString().trim();
                if (tName.isEmpty()) {
                    tName = binding1.etText.getHint().toString().trim();
                }
                Tab t = new Tab();
                t.tabName = tName;
                mainViewModel.insertTabData(t);
                dialog.dismiss();
                reSetView();
            }
        });

    }

    private void reSetView() {
        mainViewModel.getDataFromDatabase(this);
        notifyViewPagerView();
        notifyTabView();
    }

    private void notifyViewPagerView() {
        if (binding.pager.getAdapter() == null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
            adapter.setItem(mainViewModel.getTabList());
            ViewPager2 viewPager2 = binding.pager;
            viewPager2.setAdapter(adapter);
        }
        ((ViewPagerAdapter) binding.pager.getAdapter()).setItem(mainViewModel.getTabList());
        binding.pager.getAdapter().notifyDataSetChanged();
    }

    private void notifyTabView() {
        TabLayout tabLayout = binding.tab;
        new TabLayoutMediator(tabLayout, binding.pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mainViewModel.getTabList().get(position).tabName);
            }
        }).attach();
    }
}