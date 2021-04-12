package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ViewModelProviderFactory;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.create.MenuCreateViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    MenuCreateViewModel provideMenuCreateViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<MenuCreateViewModel> supplier = () -> new MenuCreateViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<MenuCreateViewModel> factory = new ViewModelProviderFactory<>(MenuCreateViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(MenuCreateViewModel.class);
    }

}
