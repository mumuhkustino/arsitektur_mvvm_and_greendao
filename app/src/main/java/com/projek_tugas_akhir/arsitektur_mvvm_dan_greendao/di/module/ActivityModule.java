package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ViewModelProviderFactory;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseActivity;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.main.MainViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.splash.SplashViewModel;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SplashViewModel> supplier = () -> new SplashViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SplashViewModel> factory = new ViewModelProviderFactory<>(SplashViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SplashViewModel.class);
    }

}
