package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module.ActivityModule;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.scope.ActivityScope;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDActivity;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.splash.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(CRUDActivity crudActivity);

    void inject(SplashActivity splashActivity);

}
