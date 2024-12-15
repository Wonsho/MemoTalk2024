package com.wons.memotalk.mainactivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.adapter.ViewPager2Adapter;
import com.wons.memotalk.mainactivity.viewmodels.ListViewModel;
import com.wons.memotalk.mainactivity.viewmodels.TabViewModel;

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

    private void updateTab(List<Tab> tabs) {

        if (tabs == null || tabs.isEmpty()) {
            insertDefaultTab();
            return;
        }

        if (binding.pager.getAdapter() == null) {
            binding.pager.setAdapter(new ViewPager2Adapter(this));
        }
        ((ViewPager2Adapter) binding.pager.getAdapter()).setData(tabs);
        Toast.makeText(this, String.valueOf(binding.pager.getAdapter().getItemCount()), Toast.LENGTH_SHORT).show();


        new TabLayoutMediator(binding.tab, binding.pager,
                (tab, position) -> tab.setText(tabViewModel.getTabs().getValue().get(position).title)
        ).attach();
    }

    private void insertDefaultTab() {
        Tab tab = new Tab();
        tab.title = getString(R.string.tab);
        tabViewModel.insert(tab);
    }

    private void setTabUtilsOnCLick() {
        binding.btnAddTab.setOnClickListener((view) -> {
            showPopupMenu();
        });
    }

    private void showPopupMenu() {
        // PopupMenu 객체 생성
        PopupMenu popupMenu = new PopupMenu(this, binding.btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu()); // 메뉴 리소스 설정

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

    private void tabRename(Tab tab) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        DialogAddTitleBinding binding1 = DialogAddTitleBinding.inflate(getLayoutInflater());
        binding1.title.setText(getString(R.string.modi_tabName));
        binding1.etText.setHint(tab.title);
        binding1.btnAddTab.setText(getString(R.string.rename));
        binding1.btnAddTab.setOnClickListener((view) -> {
            //todo rename
            if (binding1.etText.getText().toString().trim().isEmpty()) {
                dialog.dismiss();
            } else {
                tab.title = binding1.etText.getText().toString().trim();
                tabViewModel.update(tab);
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
        List<Tab> tabs = tabViewModel.getTabs().getValue();

        int count = 0;
        String title = getString(R.string.tab);

        for (Tab tab : tabs) {
            if (tab.title.length() < getString(R.string.tab).length()) {
                continue;
            }
            String defaultName = tab.title.substring(0, getString(R.string.tab).length());
            if (defaultName.equals(getString(R.string.tab))) {
                count++;
            }

        }

        if (count != 0) {
            title += " " + count;
        }

        binding1.etText.setHint(title);
        binding1.btnAddTab.setOnClickListener((view) -> {
            Tab tab = new Tab();
            if (!binding1.etText.getText().toString().trim().isEmpty()) {
                //todo insert etText Text
                tab.title = binding1.etText.getText().toString().trim();
            } else {
                //todo insert etText Hint
                tab.title = binding1.etText.getHint().toString();
            }
            tabViewModel.insert(tab);
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