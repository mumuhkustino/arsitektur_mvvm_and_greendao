package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ViewModelProviderFactory;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.function.Supplier;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    CRUDAdapter provideInsertAdapter() {
        return new CRUDAdapter(new ArrayList<>());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    CRUDViewModel provideCRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<CRUDViewModel> supplier = () -> new CRUDViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<CRUDViewModel> factory = new ViewModelProviderFactory<>(CRUDViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(CRUDViewModel.class);
    }

}
