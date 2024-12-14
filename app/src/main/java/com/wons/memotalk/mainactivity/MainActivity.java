package com.wons.memotalk.mainactivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMainBinding;
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

}