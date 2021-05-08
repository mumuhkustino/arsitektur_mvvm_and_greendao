package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class InsertViewModel extends BaseViewModel<InsertNavigator> {

    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> executionTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public InsertViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    public void insertDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
            .seedDatabaseHospital(numOfData)
                .concatMap(hospitalList -> hospitalList != null ? Flowable.fromIterable(hospitalList)
                        : Flowable.fromIterable(new ArrayList<>()))
                    .concatMap(hospital -> hospital != null ? getDataManager().insertHospital(hospital)
                            : Flowable.fromIterable(new ArrayList<>()))
                        .subscribe(aBoolean -> {
                            if (!aBoolean)
                                Log.d("CVM", "insertDatabase: 1 " + numOfData);
                        } , throwable -> getNavigator().handleError(throwable))
        );
        getCompositeDisposable().add(getDataManager()
            .seedDatabaseMedicine(numOfData)
                .concatMap(medicineList -> medicineList != null ? Flowable.fromIterable(medicineList)
                        : Flowable.fromIterable(new ArrayList<>()))
                    .concatMap(medicine -> medicine != null ? getDataManager().insertMedicine(medicine)
                            : Flowable.fromIterable(new ArrayList<>()))
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                this.numOfRecord.setValue((numOfData));
                                long endTime = System.currentTimeMillis();
                                long timeElapsed = endTime - startTime; //In MilliSeconds
                                this.executionTime.setValue(timeElapsed); //To MilliSeconds
                            } else
                                Log.d("CVM", "insertDatabase: 2 " + numOfData);
                        } , throwable -> getNavigator().handleError(throwable))
        );
    }

    public void setMedicalListLiveData(MutableLiveData<List<Medical>> medicalListLiveData) {
        this.medicalListLiveData = medicalListLiveData;
    }

    public LiveData<List<Medical>> getMedicalListLiveData() {
        return medicalListLiveData;
    }

    public LiveData<Long> getNumOfRecord() {
        return numOfRecord;
    }

    public LiveData<Long> getExecutionTime() {
        return executionTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}