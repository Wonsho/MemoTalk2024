package com.wons.memotalk.mainactivity;

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
import com.wons.memotalk.mainactivity.adapter.DemoCollectionAdapter;
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
            tabViewModel.dataLoad();
            tabViewModel.getTabs().observe(this, tabs -> {
                setViewPager();
                setTabView();
                if (tabs.isEmpty()) {
                    Tab tab = new Tab();
                    tab.title = getString(R.string.tab);
                    tabViewModel.insert(tab);
                }
            });
        }
        setTabUtilsOnCLick();
    }

    private void setViewPager() {
       if(binding.pager.getAdapter() == null) {
           binding.pager.setAdapter(new DemoCollectionAdapter(this, tabViewModel.getTabs().getValue()));
       }
    }

    private void setTabView() {
        new TabLayoutMediator(binding.tab, binding.pager,
                (tab, position) -> tab.setText(tabViewModel.getTabs().getValue().get(position).title)
        ).attach();
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
                if (menuItem.getItemId() == R.id.btn_addTab) {

                }

                if (menuItem.getItemId() == R.id.btn_d_list) {

                }

                if (menuItem.getItemId() == R.id.btn_c_tab_name) {

                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void tabRename() {

    }

    private void addTab() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();

        DialogAddTitleBinding binding1 = DialogAddTitleBinding.inflate(getLayoutInflater());

    }

    private void delTab() {
        //todo 보류 -> 현재 탭의 메모룸 메모장 다 삭제 해야됨
    }

}