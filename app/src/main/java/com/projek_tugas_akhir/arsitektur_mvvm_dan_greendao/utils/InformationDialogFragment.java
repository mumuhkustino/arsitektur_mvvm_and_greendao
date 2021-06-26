package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;

public class InformationDialogFragment extends DialogFragment {
    private Button mCloseButton;

    public InformationDialogFragment() {
    }

    public static InformationDialogFragment newInstance() {
        InformationDialogFragment frag = new InformationDialogFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.information_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCloseButton = view.findViewById(R.id.btnClose);
        mCloseButton.setOnClickListener(v -> {
            getDialog().cancel();
        });
    }

}
