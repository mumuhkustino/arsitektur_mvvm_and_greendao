package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertNavigator;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.List;

public class CRUDViewModel extends BaseViewModel {

//    private final MutableLiveData<Long> numOfRecord;

//    private final MutableLiveData<Long> excecutionTime;

//    private final MutableLiveData<List<Medical>> medicalListLiveData;

    public CRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
//        this.numOfRecord = new MutableLiveData<>();
//        this.excecutionTime = new MutableLiveData<>();
//        this.medicalListLiveData = new MutableLiveData<>();
//        fetchMedicals();
    }

//    public void fetchMedicals() {
//        setIsLoading(true);
//        getCompositeDisposable().add(getDataManager()
//            .getMedicalData()
//            .doOnNext(medicalList -> Log.d("SVM", "loadMedicalData: " + medicalList.size()))
//            .subscribeOn(getSchedulerProvider().io())
//            .observeOn(getSchedulerProvider().ui())
//            .subscribe(medicalList -> {
//                if (medicalList != null && medicalList.size() > 0) {
//                    Log.d("SVM", "loadMedicalData: " + medicalList.size());
//                    medicalListLiveData.setValue(medicalList);
//                }
//                setIsLoading(false);
//            }, throwable -> {
//                setIsLoading(false);
//                Log.d("SVM", "loadMedicalData: " + throwable);
//                getNavigator().handleError(throwable);
//            }));
//    }
//
//    public LiveData<List<Medical>> getMedicalListLiveData() {
//        return medicalListLiveData;
//    }

}