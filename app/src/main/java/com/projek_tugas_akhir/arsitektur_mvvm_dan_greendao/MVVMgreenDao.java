package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao;

import android.app.Application;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.ApplicationComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

public class MVVMgreenDao extends Application {

    public ApplicationComponent applicationComponent;

//    @Inject
//    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .application(this).build();

        applicationComponent.inject(this);
    }

}
