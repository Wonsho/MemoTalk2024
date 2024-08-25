package com.wons.memotalk.mainactivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wons.memotalk.R;
import com.wons.memotalk.databinding.ActivityMainBinding;
import com.wons.memotalk.databinding.DialogAddTitleBinding;
import com.wons.memotalk.entity.Tab;
import com.wons.memotalk.mainactivity.adapter.ViewPager2Adapter;
import com.wons.memotalk.mainactivity.service.MainService;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private MainService service;
    private ActivityMainBinding binding;

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
        if (service == null) {
            this.service = new MainService(getApplicationContext());
        }
        setOnClick();
        setAdapter();
    }

    private void setOnClick() {
        binding.btnAddTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        DialogAddTitleBinding binding = DialogAddTitleBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(binding.getRoot()).create();
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

        String str = getString(R.string.tab);
        str += service.getLastTabId();
        binding.etText.setHint(str);

        binding.btnAddTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = binding.etText.getText().toString().trim();

                if (str.isEmpty()) {
                    str = binding.etText.getHint().toString().trim();
                }
                service.insertTabData(str);
                setAdapter();
                dialog.dismiss();
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void setAdapter() {
        ViewPager2 pager2 = binding.pager;
        if (pager2.getAdapter() == null) {
            ViewPager2Adapter adapter = new ViewPager2Adapter(getSupportFragmentManager(), getLifecycle(), (ArrayList<Tab>) service.getAllTab());
            pager2.setAdapter(adapter);
        }
        pager2.getAdapter().notifyDataSetChanged();
        TabLayout tabLayout = binding.tab;
        new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(service.getTabByPosition(position).tabName);
            }
        }).attach();

        for (int i=0; i<tabLayout.getTabCount(); i++) {
            TooltipCompat.setTooltipText(Objects.requireNonNull(tabLayout.getTabAt(i)).view, null);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition(); // syntactic sugar
                pager2.setCurrentItem(tabPosition, true);

                for (int i=0; i<tabLayout.getTabCount(); i++) {
                    TooltipCompat.setTooltipText(Objects.requireNonNull(tabLayout.getTabAt(i)).view, null);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}