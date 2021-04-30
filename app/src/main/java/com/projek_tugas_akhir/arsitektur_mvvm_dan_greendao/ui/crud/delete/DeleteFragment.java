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
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.FragmentDeleteBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDNavigator;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDViewModel;

import java.util.List;

import javax.inject.Inject;

public class DeleteFragment extends BaseFragment<FragmentDeleteBinding, CRUDViewModel> implements CRUDNavigator,
        CRUDAdapter.CRUDAdapterListener {

    @Inject
    CRUDAdapter deleteAdapter;

    FragmentDeleteBinding fragmentDeleteBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

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
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onClick() {
        if (fragmentDeleteBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentDeleteBinding.editTextNumData.getText().toString());
                viewModel.deleteDatabase(numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        deleteAdapter.deleteItems(medicalList);
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentDeleteBinding.deleteRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentDeleteBinding.deleteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentDeleteBinding.deleteRecyclerView.setAdapter(deleteAdapter);
    }

    @Override
    public void onRetryClick() {
        if (fragmentDeleteBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentDeleteBinding.editTextNumData.getText().toString());
                viewModel.selectDatabase(numOfData);
                viewModel.deleteDatabase(numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}