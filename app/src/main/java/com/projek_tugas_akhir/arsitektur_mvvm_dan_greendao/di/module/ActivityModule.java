package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ViewModelProviderFactory;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseActivity;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDPagerAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.function.Supplier;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Provides
//    CRUDViewModel provideCrudViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
//        Supplier<CRUDViewModel> supplier = () -> new CRUDViewModel(dataManager, schedulerProvider);
//        ViewModelProviderFactory<CRUDViewModel> factory = new ViewModelProviderFactory<>(CRUDViewModel.class, supplier);
//        return new ViewModelProvider(activity, factory).get(CRUDViewModel.class);
//    }
//
//    @Provides
//    CRUDPagerAdapter providedCrudPagerAdapter() {
//        return new CRUDPagerAdapter(activity.getSupportFragmentManager());
//    }

}
