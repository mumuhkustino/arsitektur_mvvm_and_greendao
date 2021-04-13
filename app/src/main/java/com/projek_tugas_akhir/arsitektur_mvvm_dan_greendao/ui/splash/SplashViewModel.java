package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.splash;

import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager,
                           SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startSeeding() {
        Log.d("SVM", "startSeeding: Mulai");
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital()
                .flatMap(aBoolean -> getDataManager().seedDatabaseMedicine()
                .flatMap(aBoolean1 -> getDataManager().seedDatabaseDisease()
                .flatMap(aBoolean2 -> getDataManager().seedDatabaseSymptom())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    Log.d("SVM", "startSeeding: Selesai");
                    decideNextActivity();
                }, throwable -> {
                    Log.d("SVM", "startSeeding: Error");
                    decideNextActivity();
                }));
    }

    private void decideNextActivity() {
        getNavigator().openCRUDActivity();
    }

}
