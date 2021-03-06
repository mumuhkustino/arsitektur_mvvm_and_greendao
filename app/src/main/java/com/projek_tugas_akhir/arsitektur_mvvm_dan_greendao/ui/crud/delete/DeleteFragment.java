package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete;

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
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentDeleteBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;

import javax.inject.Inject;
import javax.inject.Named;

public class DeleteFragment extends BaseFragment<FragmentDeleteBinding, DeleteViewModel> implements DeleteNavigator,
        DeleteAdapter.DeleteAdapterListener {

    @Inject //Penggunaan dependency injection untuk adapter delete
    @Named("delete")
    DeleteAdapter deleteAdapter; //Deklarasi Adapter pada view Delete

    FragmentDeleteBinding fragmentDeleteBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ExecutionTimePreference executionTimePreference;

    public static DeleteFragment newInstance() {
        Bundle args = new Bundle();
        DeleteFragment fragment = new DeleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_delete;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        deleteAdapter.setListener(this);

        executionTimePreference = new ExecutionTimePreference(getBaseActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentDeleteBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onClick() {
        if (fragmentDeleteBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentDeleteBinding.editTextNumData.getText().toString());
                viewModel.deleteDatabase(executionTimePreference, numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentDeleteBinding.deleteRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentDeleteBinding.deleteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentDeleteBinding.deleteRecyclerView.setAdapter(deleteAdapter);

        if (!executionTimePreference.getExecutionTime().getDatabaseDeleteTime().isEmpty())
            fragmentDeleteBinding.textViewTimeDeleteDB
                    .setText("TIME DB (MS) : " +
                            executionTimePreference.getExecutionTime().getDatabaseDeleteTime());

        if (!executionTimePreference.getExecutionTime().getAllDeleteTime().isEmpty())
            fragmentDeleteBinding.textViewTimeDeleteAll
                    .setText("TIME ALL (MS) : " +
                            executionTimePreference.getExecutionTime().getAllDeleteTime());

        if (!executionTimePreference.getExecutionTime().getViewDeleteTime().isEmpty())
            fragmentDeleteBinding.textViewTimeDeleteView
                    .setText("TIME VIEW (MS) : " +
                            executionTimePreference.getExecutionTime().getViewDeleteTime());

        if (!executionTimePreference.getExecutionTime().getNumOfRecordDelete().isEmpty())
            fragmentDeleteBinding.textViewRecord
                    .setText("RECORDS : " +
                            executionTimePreference.getExecutionTime().getNumOfRecordDelete());
    }

    @Override
    public void onRetryClick() {
        if (fragmentDeleteBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentDeleteBinding.editTextNumData.getText().toString());
                viewModel.deleteDatabase(executionTimePreference, numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}
