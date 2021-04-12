package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component;

import android.app.Application;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.MVVMgreenDao;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module.ApplicationModule;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MVVMgreenDao mvvMgreenDao);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();

    }

}
