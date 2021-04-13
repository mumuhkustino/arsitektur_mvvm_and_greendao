package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BR;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.InsertFragmentBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class InsertFragment extends BaseFragment<InsertFragmentBinding, InsertViewModel> implements InsertNavigator,
        InsertAdapter.InsertAdapterListener {

    @Inject
    InsertAdapter insertAdapter;

    InsertFragmentBinding insertFragmentBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    public static InsertFragment newInstance() {
        Bundle args = new Bundle();
        InsertFragment fragment = new InsertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.insert_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
//        viewModel.setListener(this);
        insertAdapter.setListener(this);
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }



    @Override
    public void onRetryClick() {
        if (insertFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(insertFragmentBinding.editTextNumData.getText().toString());
                viewModel.fetchMedicals(numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        insertFragmentBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        insertAdapter.addItems(medicalList);
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        insertFragmentBinding.insertRecyclerView.setLayoutManager(linearLayoutManager);
        insertFragmentBinding.insertRecyclerView.setItemAnimator(new DefaultItemAnimator());
        insertFragmentBinding.insertRecyclerView.setAdapter(insertAdapter);
    }

    @Override
    public void onItemClick() {
        if (insertFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(insertFragmentBinding.editTextNumData.getText().toString());
                viewModel.fetchMedicals(numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}
