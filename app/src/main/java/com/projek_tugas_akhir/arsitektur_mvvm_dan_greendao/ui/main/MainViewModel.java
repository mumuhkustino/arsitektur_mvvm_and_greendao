package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.main;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;

import java.util.List;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";

    public static final int NO_ACTION = -1, ACTION_ADD_ALL = 0, ACTION_DELETE_SINGLE = 1;

    private final ObservableField<String> appVersion = new ObservableField<>();

    private final MutableLiveData<List<Hospital>> hospitalData;

    private final ObservableList<Hospital> hospitalDataList = new ObservableArrayList<>();

    private int action = NO_ACTION;

//    public final ObservableField<String> appVersion = new ObservableField<>();

    public MainViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        hospitalData = new MutableLiveData<>();
        loadHospital();
    }

    public int getAction() {
        return action;
    }

    public ObservableField<String> getAppVersion() {
        return appVersion;
    }

    public LiveData<List<Hospital>> getHospitalData() {
        return hospitalData;
    }

    public ObservableList<Hospital> getHospitalDataList() {
        return hospitalDataList;
    }

    public void setHospitalDataList(List<Hospital> hospitalDataList) {
        action = ACTION_ADD_ALL;
        this.hospitalDataList.clear();
        this.hospitalDataList.addAll(hospitalDataList);
    }

    public void loadHospital() {
        getCompositeDisposable().add(getDataManager()
        .getAllHospital()
        .doOnNext(list -> Log.d(TAG, "loadHospital: " + list.size()))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(hospitalList -> {
            if (hospitalList != null) {
                Log.d(TAG, "loadHospital: " + hospitalList.size());
                action = ACTION_ADD_ALL;
                hospitalData.setValue(hospitalList);
            }
        }, throwable -> {
            Log.d(TAG, "loadHospital: " + throwable);
        }));
    }

    public void removeHospital() {
        action = ACTION_DELETE_SINGLE;
        hospitalData.getValue().remove(0);
    }

    public void updateAppVersion(String version) {
        this.appVersion.set(version);
    }

}
