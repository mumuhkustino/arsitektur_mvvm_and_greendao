package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.create;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;

public class MenuInsertFragment extends Fragment {
//    private FragmentMenuCreateBinding fragmentMenuCreateBinding;
    private MenuCreateViewModel viewModel;
    private long beforeTime = System.currentTimeMillis();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        fragmentMenuCreateBinding = FragmentMenuCreateBinding.inflate(getLayoutInflater());
//        getActivity().setContentView(fragmentMenuCreateBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(MenuCreateViewModel.class);

//        getActivity().findViewById(R.id.btn_menu_create).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewModel.calculateTime(beforeTime, System.currentTimeMillis());
//            }
//        });

        displayExecutionTime();
    }

    private void displayExecutionTime() {
//        fragmentMenuCreateBinding.textExecutionTime.setText(String.valueOf(viewModel.executionTime));
    }
}
