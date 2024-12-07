package com.wons.memotalk.mainactivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
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
import com.wons.memotalk.entity.memoList.MainMemoListModel;
import com.wons.memotalk.mainactivity.adapter.ViewPagerAdapter;
import com.wons.memotalk.mainactivity.viewmodel.MainViewModel;
import com.wons.memotalk.mainactivity.viewmodel.MemoListViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private MemoListViewModel memoListViewModel;


    public ArrayList<MainMemoListModel> getListModel(Long id) {
        return memoListViewModel.getMemoListByTabId(this, id);
    }

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
                //todo 팝업 메뉴 띄우기
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        if (id == R.id.btn_d_list) {
                            //todo 리스트 삭제
                        }

                        if (id == R.id.btn_d_tab) {
                            //todo 탭 삭제
                            deleteTabData();
                        }

                        if (id == R.id.btn_a_tab) {
                            showDialog();
                        }

                        if (id == R.id.btn_a_list) {
                            //todo add List
                            MainFragment fragment = ((ViewPagerAdapter) binding.pager.getAdapter()).getFragments(binding.pager.getCurrentItem());
                            fragment.addList();
                        }

                        if (id == R.id.btn_c_list) {
                            //todo reName list
                        }

                        if (id == R.id.btn_c_tab) {
                            //todo reName tab
                            changeThisTabName();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }


    //todo 나중에 추가 할 기능
    private void deleteTabData() {
        Toast.makeText(this, "기능 추가 전", Toast.LENGTH_LONG).show();
        int currentPage = binding.pager.getCurrentItem();
        Tab tab = ((ViewPagerAdapter) binding.pager.getAdapter()).getTabById(currentPage);

        if (binding.pager.getAdapter().getItemCount() == 1) {
            Toast.makeText(this, getString(R.string.tab_delete_notion), Toast.LENGTH_LONG).show();
            return;
        }

    }

    private void changeThisTabName() {
        int currentPage = binding.pager.getCurrentItem();
        Tab tab = ((ViewPagerAdapter) binding.pager.getAdapter()).getTabById(currentPage);
        Long id = tab.id;
        String title = tab.tabName;
        reNameTab(title, id);
        //todo 다이로그 띄우기
    }

    private void reNameTab(String oldTitle, Long id) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        DialogAddTitleBinding binding1 = DialogAddTitleBinding.inflate(getLayoutInflater());
        builder.setView(binding1.getRoot());
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding1.etText.setHint(oldTitle);
        binding1.title.setText(getString(R.string.change_tab));
        binding1.btnCancel.setOnClickListener((view) -> {
            //todo 취소
            dialog.dismiss();
        });

        binding1.btnAddTab.setText(getString(R.string.rename));
        binding1.btnAddTab.setOnClickListener((view) -> {
            //todo 변경
            if (!binding1.etText.getText().toString().trim().isEmpty()) {
                mainViewModel.changeTabName(getApplicationContext(), id, binding1.etText.getText().toString().trim());
            }

            notifyTabView();
            dialog.dismiss();

        });
        dialog.show();
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
        dialog = builder.create();
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