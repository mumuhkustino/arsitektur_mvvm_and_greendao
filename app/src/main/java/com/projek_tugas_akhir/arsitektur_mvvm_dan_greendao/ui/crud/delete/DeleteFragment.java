package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;

public class DeleteFragment extends Fragment {

    private DeleteViewModel mViewModel;

    public static DeleteFragment newInstance() {
        return new DeleteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delete_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DeleteViewModel.class);
        // TODO: Use the ViewModel
    }

}
