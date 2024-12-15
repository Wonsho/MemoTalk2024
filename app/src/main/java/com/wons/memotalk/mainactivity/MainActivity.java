package com.wons.memotalk.mainactivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMainBinding;
import com.wons.memotalk.databinding.DialogAddTitleBinding;
import com.wons.memotalk.entity.TabItem;
import com.wons.memotalk.mainactivity.adapter.ViewPager2Adapter;
import com.wons.memotalk.mainactivity.viewmodels.ListViewModel;
import com.wons.memotalk.mainactivity.viewmodels.TabViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ListViewModel listViewModel;
    private TabViewModel tabViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        if (binding == null) {
            binding = ActivityMainBinding.inflate(getLayoutInflater());
        }
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (listViewModel == null) {
            listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        }

        if (tabViewModel == null) {
            tabViewModel = new ViewModelProvider(this).get(TabViewModel.class);
            tabViewModel.getTabs().observe(this, tabs -> {
                updateTab(tabs);
            });
        }
        setTabUtilsOnCLick();
    }

    private void updateTab(java.util.List<TabItem> tabItems) {

        if (tabItems == null || tabItems.isEmpty()) {
            insertDefaultTab();
            return;
        }

        if (binding.pager.getAdapter() == null) {
            binding.pager.setAdapter(new ViewPager2Adapter(this));
        }
        ((ViewPager2Adapter) binding.pager.getAdapter()).setData(tabItems);

        new TabLayoutMediator(binding.tabItem, binding.pager,
                (tab, position) -> tab.setText(tabViewModel.getTabs().getValue().get(position).title)
        ).attach();
    }

    private void insertDefaultTab() {
        TabItem tabItem = new TabItem();
        tabItem.title = getString(R.string.tabItem);
        tabViewModel.insert(tabItem);
    }

    private void setTabUtilsOnCLick() {
        binding.btnAddTab.setOnClickListener((view) -> {
            showPopupMenu();
        });
    }

    private void showPopupMenu() {
        // PopupMenu 객체 생성
        PopupMenu popupMenu = new PopupMenu(this, binding.btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_list, popupMenu.getMenu()); // 메뉴 리소스 설정

        // 메뉴 항목 클릭 리스너 설정
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.btn_add_list) {
                    addTab();
                }

                if (menuItem.getItemId() == R.id.btn_d_list) {
                    //todo 보류
                }

                if (menuItem.getItemId() == R.id.btn_c_list_name) {
                    int id = binding.pager.getCurrentItem();
                    tabRename(tabViewModel.getByIndex(id));
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void tabRename(TabItem tabItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        DialogAddTitleBinding binding1 = DialogAddTitleBinding.inflate(getLayoutInflater());
        binding1.title.setText(getString(R.string.modi_tabName));
        binding1.etText.setHint(tabItem.title);
        binding1.btnAddTab.setText(getString(R.string.rename));
        binding1.btnAddTab.setOnClickListener((view) -> {
            //todo rename
            if (binding1.etText.getText().toString().trim().isEmpty()) {
                dialog.dismiss();
            } else {
                tabItem.title = binding1.etText.getText().toString().trim();
                tabViewModel.update(tabItem);
                dialog.dismiss();
            }
        });

        binding1.btnCancel.setOnClickListener((view) -> {
            dialog.dismiss();
        });
        dialog.setView(binding1.getRoot());
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void addTab() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();

        DialogAddTitleBinding binding1 = DialogAddTitleBinding.inflate(getLayoutInflater());
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding1.title.setText(R.string.add_tab);

        List<TabItem> tabItems = tabViewModel.getTabs().getValue();

        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            indexes.add(i);
        }

        String title = getString(R.string.tabItem);

        for (TabItem tabItem : tabItems) {
            if (tabItem.title.length() < getString(R.string.tabItem).length()) {
                continue;
            }

            try {
                Integer index = Integer.parseInt(tabItem.title.trim().substring(title.length(), tabItem.title.length()).trim());
                indexes.remove(index);
            } catch (Exception e) {
                Log.i("MainActivity", "value is not integer");
            }
        }
        title += " " + indexes.get(0);
        binding1.etText.setHint(title);
        binding1.btnAddTab.setOnClickListener((view) -> {
            TabItem tabItem = new TabItem();
            if (!binding1.etText.getText().toString().trim().isEmpty()) {
                //todo insert etText Text
                tabItem.title = binding1.etText.getText().toString().trim();
            } else {
                //todo insert etText Hint
                tabItem.title = binding1.etText.getHint().toString();
            }
            tabViewModel.insert(tabItem);
            dialog.dismiss();
        });

        binding1.btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.setView(binding1.getRoot());
        dialog.show();

    }

    private void delTab() {
        //todo 보류 -> 현재 탭의 메모룸 메모장 다 삭제 해야됨
    }

}