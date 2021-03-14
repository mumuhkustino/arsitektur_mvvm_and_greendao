package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private Long beforeTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
//        getActivity().setContentView(fragmentHomeBinding.getRoot());
//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//
//        fragmentHomeBinding.btnMenuCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), String.valueOf(System.currentTimeMillis() - beforeTime), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        fragmentHomeBinding.btnMenuRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        fragmentHomeBinding.btnMenuUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        fragmentHomeBinding.btnMenuDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final Button btnMenuCreate = root.findViewById(R.id.btn_menu_create);
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getTime().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                beforeTime = aLong;
            }
        });
        btnMenuCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), String.valueOf(System.currentTimeMillis() - beforeTime), Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }
}
