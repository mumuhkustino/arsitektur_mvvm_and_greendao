package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao;

import android.app.Application;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.ApplicationComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.DaggerApplicationComponent;

public class MVVMgreenDao extends Application {

    public ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .application(this).build();

        applicationComponent.inject(this);
    }

}
