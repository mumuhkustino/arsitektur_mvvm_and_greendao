package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BR;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentUpdateBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDNavigator;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDViewModel;

import java.util.List;

import javax.inject.Inject;

public class UpdateFragment extends BaseFragment<FragmentUpdateBinding, CRUDViewModel> implements CRUDNavigator,
        CRUDAdapter.CRUDAdapterListener {

    @Inject
    CRUDAdapter updateAdapter;

    FragmentUpdateBinding fragmentUpdateBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    public static UpdateFragment newInstance() {
        Bundle args = new Bundle();
        UpdateFragment fragment = new UpdateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_update;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        updateAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentUpdateBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onRetryClick() {
        if (fragmentUpdateBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentUpdateBinding.editTextNumData.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onClick() {
        if (fragmentUpdateBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentUpdateBinding.editTextNumData.getText().toString());
                viewModel.updateDatabase(numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        updateAdapter.updateItems(medicalList);
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentUpdateBinding.updateRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentUpdateBinding.updateRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentUpdateBinding.updateRecyclerView.setAdapter(updateAdapter);
    }
}