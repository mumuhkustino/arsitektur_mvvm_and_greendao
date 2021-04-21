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
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentSelectBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDNavigator;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDViewModel;

import java.util.List;

import javax.inject.Inject;

public class SelectFragment extends BaseFragment<FragmentSelectBinding, CRUDViewModel> implements CRUDNavigator,
        CRUDAdapter.CRUDAdapterListener {

    @Inject
    CRUDAdapter selectAdapter;

    FragmentSelectBinding selectFragmentBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

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
//        viewModel.setListener(this);
        selectAdapter.setListener(this);
        viewModel.fetchMedicals();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onRetryClick() {
        if (selectFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(selectFragmentBinding.editTextNumData.getText().toString());
                viewModel.fetchMedicals(numOfData);
//                viewModel.fetchMedicals();
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
        selectFragmentBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        selectAdapter.selectItems(medicalList);
    }

    private void setUp() {
        viewModel.fetchMedicals();
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectFragmentBinding.selectRecyclerView.setLayoutManager(linearLayoutManager);
        selectFragmentBinding.selectRecyclerView.setItemAnimator(new DefaultItemAnimator());
        selectFragmentBinding.selectRecyclerView.setAdapter(selectAdapter);
    }

    @Override
    public void onClick() {
        if (selectFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(selectFragmentBinding.editTextNumData.getText().toString());
                viewModel.fetchMedicals(numOfData);
                viewModel.setIsLoading(false);
//                viewModel.fetchMedicals();
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
                viewModel.fetchMedicals();
            }
        } else {
            viewModel.fetchMedicals();
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}
