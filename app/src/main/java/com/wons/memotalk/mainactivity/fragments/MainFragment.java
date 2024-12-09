package com.wons.memotalk.mainactivity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wons.memotalk.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return FragmentMainBinding.inflate(inflater).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

//        Bundle args = getArguments();
//        ((TextView) view.findViewById(android.R.id.text1))
//                .setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }
}