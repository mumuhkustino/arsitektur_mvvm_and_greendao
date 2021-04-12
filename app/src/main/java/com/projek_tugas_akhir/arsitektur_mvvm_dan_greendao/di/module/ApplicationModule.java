package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module;

import android.app.Application;
import android.content.Context;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.AppDataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.AppDbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.DatabaseInfo;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.AppConstants;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.AppSchedulerProvider;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) { return application;}

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() { return new AppSchedulerProvider(); }

}
