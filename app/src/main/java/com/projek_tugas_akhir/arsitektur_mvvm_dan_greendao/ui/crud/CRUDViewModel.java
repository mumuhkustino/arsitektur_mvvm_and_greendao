package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.BackpressureStrategy;

public class CRUDViewModel extends BaseViewModel<CRUDNavigator> {

    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> executionTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public CRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
//        fetchMedicals();
    }

    public void fetchExecutionNumOfRecord() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getMedical()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toFlowable()
                .subscribe(medicalList -> {
                            if (medicalList != null && !medicalList.isEmpty()) {
                                numOfRecord.setValue((long) medicalList.size());
                                Log.d("CVM", "fetchExecutionNumOfRecord: " + medicalList.size());
                            }
                            setIsLoading(false);
                        }, throwable -> {
                            setIsLoading(false);
                            getNavigator().handleError(throwable);
                        }
                ));
    }

    public void fetchMedicals() {
        setIsLoading(true);
        long startTime = System.nanoTime();
            executionTime.setValue(startTime);
        getCompositeDisposable().add(getDataManager()
                .getMedical()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toFlowable()
                .subscribe(medicalList -> {
            if (medicalList != null && !medicalList.isEmpty()) {
                numOfRecord.setValue((long) medicalList.size());
                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime; //In NanoSeconds
                executionTime.setValue(timeElapsed/1000000); //To MilliSeconds
                medicalListLiveData.setValue(medicalList);
                Log.d("CVM", "fetchMedicals: " + medicalList.size());
            }
            setIsLoading(false);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }
            ));
    }

    public void fetchMedicals(Long numOfData) {
//    public void fetchMedicals() {
        setIsLoading(true);
        long startTime = System.nanoTime();
        executionTime.setValue(startTime);
        getCompositeDisposable().add(getDataManager()
//            .getMedical()
            .getMedical(numOfData)
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .toFlowable()
            .subscribe(medicalList -> {
                    if (medicalList != null && !medicalList.isEmpty()) {
                        numOfRecord.setValue((long) medicalList.size());
                        long endTime = System.nanoTime();
                        long timeElapsed = endTime - startTime; //In NanoSeconds
                        executionTime.setValue(timeElapsed/1000000); //To MilliSeconds
                        medicalListLiveData.setValue(medicalList);
                        Log.d("CVM", "fetchMedicals 2 : " + medicalList.size());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }
            ));
    }

    public void startSeeding(Long numOfData) {
        Log.d("CVM", "startSeeding: Mulai " + Math.round(Math.pow(numOfData, 0.25)));
        Long num = Math.round(Math.pow(numOfData, 0.25));
        setIsLoading(true);
        long startTime = System.nanoTime();
        executionTime.setValue(startTime);
        getCompositeDisposable().add(getDataManager()
//                .seedDatabaseHospital()
                .seedDatabaseHospital(num)
                .flatMap(aBoolean -> getDataManager().seedDatabaseMedicine(num)
                        .flatMap(aBoolean1 -> getDataManager().seedDatabaseDisease(num)
                                .flatMap(aBoolean2 -> getDataManager().seedDatabaseSymptom(num))
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toFlowable(BackpressureStrategy.DROP)
                .subscribe(aBoolean -> {
                    if (aBoolean != null) {
//                        numOfRecord.setValue(numOfData);
                        long endTime = System.nanoTime();
                        long timeElapsed = endTime - startTime; //In NanoSeconds
                        executionTime.setValue(timeElapsed/1000000); //To MilliSeconds
//                        medicalListLiveData.setValue(medicalList);
                        Log.d("CVM", "startSeeding: " + aBoolean);
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    Log.d("CVM", "startSeeding: " + throwable.getMessage());
//                    decideNextActivity();
                }));
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