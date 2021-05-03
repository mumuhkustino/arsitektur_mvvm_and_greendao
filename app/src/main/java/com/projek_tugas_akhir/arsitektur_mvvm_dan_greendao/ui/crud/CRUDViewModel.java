package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;

public class CRUDViewModel extends BaseViewModel<CRUDNavigator> {

    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> executionTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public CRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    public void insertDatabase(Long numOfData) {
        setIsLoading(true);
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital(numOfData)
                .subscribeOn(getSchedulerProvider().fromA())
                .concatMap(aBoolean -> getDataManager().seedDatabaseMedicine(numOfData))
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                        if (aBoolean) {
                            this.numOfRecord.postValue((numOfData));
                            long endTime = System.currentTimeMillis();
                            long timeElapsed = endTime - startTime; //In MilliSeconds
                            this.executionTime.postValue(timeElapsed); //To MilliSeconds
                            Log.d("CVM", "insertDatabase: " + numOfData);
                        }
                        setIsLoading(false);
                    }
                , throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }
                )
        );
    }

    public void refreshMedical(Long numOfData, List<Hospital> hospitalList) {
        this.medicalListLiveData.postValue(new ArrayList<>());
        int index = 0;
        int size = 0;
        List<Medical> medicals = new ArrayList<>();
        for (int i = 0; i < hospitalList.size(); i++) {
            if (hospitalList.get(i) != null && hospitalList.get(i)
                    .getMedicineList() != null) {
                int j = 0;
                size += hospitalList.get(i).getMedicineList().size();
                while (j < hospitalList.get(i).getMedicineList().size() && index < numOfData) {
                    medicals.add(new Medical(hospitalList.get(i).getName(),
                            hospitalList.get(i).getMedicineList().get(j).getName()));
                    index++;
                    j++;
                }
            }
            if (index >= numOfData) break;
        }
        Log.d("CVM", "refreshMedical = " + size);
        this.medicalListLiveData.postValue(medicals);
    }

    public void selectDatabase(Long numOfData) {
//        setIsLoading(true);
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
            .getAllHospital()
            .subscribeOn(getSchedulerProvider().fromA())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(hospitalList -> {
                if (hospitalList != null) {
                    refreshMedical(numOfData, hospitalList);
                    this.numOfRecord.postValue(numOfData);
                    long endTime = System.currentTimeMillis();
                    long timeElapsed = endTime - startTime; //In MilliSeconds
                    this.executionTime.postValue(timeElapsed); //To MilliSeconds
                }
//                setIsLoading(false);
            }, throwable -> {
                Log.d("CVM", "selectDatabase: " + throwable.getMessage());
//                setIsLoading(false);
            }));
    }

    public void updateDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        AtomicInteger index = new AtomicInteger();
        index.set(0);
        getCompositeDisposable().add(getDataManager()
                .getAllHospital()
                .subscribeOn(getSchedulerProvider().io())
                .concatMap(hospitalList -> Flowable.fromIterable(hospitalList)
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .concatMap(hospital -> Flowable.fromIterable(hospital.getMedicineList())
                                .observeOn(getSchedulerProvider().ui())
                                .subscribeOn(getSchedulerProvider().io())
                                .concatMap(medicine -> {
                                    if (index.get() < numOfData) {
                                        medicine.setName(medicine.getName() + " NEW");
                                        index.set(index.get() + 1);
                                        return getDataManager().updateDatabaseMedicine(medicine);
                                    } else
                                        return Flowable.just(false);
                                })
                        )
                )
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        this.numOfRecord.postValue((long) index.get());
                        long endTime = System.currentTimeMillis();
                        long timeElapsed = endTime - startTime; //In MilliSeconds
                        this.executionTime.postValue(timeElapsed); //To MilliSeconds
                    } else
                        Log.d("CVM", "updateDatabase: " + index.get());
                }, throwable -> {
                    Log.d("CVM", "updateDatabase: " + throwable.getMessage());
                }));
    }

    public void deleteDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        AtomicInteger index = new AtomicInteger();
        index.set(numOfData.intValue());
        getCompositeDisposable().add(getDataManager()
                .getAllHospital()
                .subscribeOn(getSchedulerProvider().io())
                .concatMap(hospitalList -> Flowable.fromIterable(hospitalList)
                    .observeOn(getSchedulerProvider().ui())
                    .subscribeOn(getSchedulerProvider().io())
                    .concatMap(hospital -> Flowable.fromIterable(hospital.getMedicineList()))
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .concatMap(medicine -> {
                            if (index.get() >= 0) {
                                index.set(index.get() - 1);
                                return getDataManager().deleteDatabaseMedicine(medicine);
                            } else
                                return Flowable.just(false);
                        })
                )
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        this.numOfRecord.postValue((long) index.get());
                        long endTime = System.currentTimeMillis();
                        long timeElapsed = endTime - startTime; //In MilliSeconds
                        this.executionTime.postValue(timeElapsed); //To MilliSeconds
                    } else
                        Log.d("CVM", "deleteDatabase: " + index.get());
                }, throwable -> {
                    Log.d("CVM", "deleteDatabase: " + throwable.getMessage());
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