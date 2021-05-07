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
import java.util.concurrent.atomic.AtomicInteger;

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
//        setIsLoading(true);
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital(numOfData)
//                .subscribeOn(getSchedulerProvider().io())
                .concatMap(aBoolean -> getDataManager().seedDatabaseMedicine(numOfData))
//                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                            if (aBoolean) {
//                                this.numOfRecord.postValue((numOfData));
                                this.numOfRecord.setValue((numOfData));
                                long endTime = System.currentTimeMillis();
                                long timeElapsed = endTime - startTime; //In MilliSeconds
//                                this.executionTime.postValue(timeElapsed); //To MilliSeconds
                                this.executionTime.setValue(timeElapsed); //To MilliSeconds
                                Log.d("CVM", "insertDatabase: " + numOfData);
                            } else
                                Log.d("CVM", "insertDatabase: " + numOfData);
//                            setIsLoading(false);
                        }
                        , throwable -> {
//                            setIsLoading(false);
                            getNavigator().handleError(throwable);
                        }
                )
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