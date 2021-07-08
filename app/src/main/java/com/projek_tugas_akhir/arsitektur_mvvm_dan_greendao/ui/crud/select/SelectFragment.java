package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select;

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
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentSelectBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;

import javax.inject.Inject;
import javax.inject.Named;

public class SelectFragment extends BaseFragment<FragmentSelectBinding, SelectViewModel> implements SelectNavigator,
        SelectAdapter.SelectAdapterListener {

    @Inject //Penggunaan dependency injection untuk adapter select
    @Named("select")
    SelectAdapter selectAdapter; //Deklarasi Adapter pada view Select

    FragmentSelectBinding selectFragmentBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ExecutionTimePreference executionTimePreference;

    public static SelectFragment newInstance() {
        Bundle args = new Bundle();
        SelectFragment fragment = new SelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_select;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        selectAdapter.setListener(this);

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
        selectFragmentBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectFragmentBinding.selectRecyclerView.setLayoutManager(linearLayoutManager);
        selectFragmentBinding.selectRecyclerView.setItemAnimator(new DefaultItemAnimator());
        selectFragmentBinding.selectRecyclerView.setAdapter(selectAdapter);

        if (!executionTimePreference.getExecutionTime().getDatabaseSelectTime().isEmpty())
            selectFragmentBinding.textViewTimeSelectDB
                    .setText("TIME DB (MS) : " +
                            executionTimePreference.getExecutionTime().getDatabaseSelectTime());

        if (!executionTimePreference.getExecutionTime().getAllSelectTime().isEmpty())
            selectFragmentBinding.textViewTimeSelectAll
                    .setText("TIME ALL (MS) : " +
                            executionTimePreference.getExecutionTime().getAllSelectTime());

        if (!executionTimePreference.getExecutionTime().getViewSelectTime().isEmpty())
            selectFragmentBinding.textViewTimeSelectView
                    .setText("TIME VIEW (MS) : " +
                            executionTimePreference.getExecutionTime().getViewSelectTime());

        if (!executionTimePreference.getExecutionTime().getNumOfRecordSelect().isEmpty())
            selectFragmentBinding.textViewRecord
                    .setText("RECORDS : " +
                            executionTimePreference.getExecutionTime().getNumOfRecordSelect());

        selectFragmentBinding.fabDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFragmentBinding.selectRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        selectFragmentBinding.selectRecyclerView.scrollToPosition(selectAdapter.getItemCount() - 1);
                    }
                });
            }
        });
    }

    @Override
    public void onClick() {
        if (selectFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(selectFragmentBinding.editTextNumData.getText().toString());
                viewModel.selectDatabase(executionTimePreference, numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}
