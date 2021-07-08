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
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.ExecutionTimePreference;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentInsertBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;

import javax.inject.Inject;
import javax.inject.Named;

public class InsertFragment extends BaseFragment<FragmentInsertBinding, InsertViewModel> implements InsertNavigator,
        InsertAdapter.InsertAdapterListener {

    @Inject //Penggunaan dependency injection untuk adapter insert
    @Named("insert")
    InsertAdapter insertAdapter; //Deklarasi Adapter pada view Insert

    FragmentInsertBinding insertFragmentBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ExecutionTimePreference executionTimePreference;

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
        return R.layout.fragment_insert;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        insertAdapter.setListener(this);
        executionTimePreference = new ExecutionTimePreference(getBaseActivity());
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        insertFragmentBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        insertFragmentBinding.insertRecyclerView.setLayoutManager(linearLayoutManager);
        insertFragmentBinding.insertRecyclerView.setItemAnimator(new DefaultItemAnimator());
        insertFragmentBinding.insertRecyclerView.setAdapter(insertAdapter);

        if (!executionTimePreference.getExecutionTime().getDatabaseInsertTime().isEmpty())
            insertFragmentBinding.textViewTimeInsertDB
                    .setText("TIME DB (MS) : " +
                            executionTimePreference.getExecutionTime().getDatabaseInsertTime());
        if (!executionTimePreference.getExecutionTime().getAllInsertTime().isEmpty())
            insertFragmentBinding.textViewTimeInsertAll
                    .setText("TIME ALL (MS) : " +
                            executionTimePreference.getExecutionTime().getAllInsertTime());
        if (!executionTimePreference.getExecutionTime().getViewInsertTime().isEmpty())
            insertFragmentBinding.textViewTimeInsertView
                    .setText("TIME VIEW (MS) : " +
                            executionTimePreference.getExecutionTime().getViewInsertTime());
        if (!executionTimePreference.getExecutionTime().getNumOfRecordInsert().isEmpty())
            insertFragmentBinding.textViewRecord
                    .setText("RECORDS : " +
                            executionTimePreference.getExecutionTime().getNumOfRecordInsert());
    }

    @Override
    public void onClick() {
        if (insertFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(insertFragmentBinding.editTextNumData.getText().toString());
                viewModel.insertDatabase(executionTimePreference, numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}
