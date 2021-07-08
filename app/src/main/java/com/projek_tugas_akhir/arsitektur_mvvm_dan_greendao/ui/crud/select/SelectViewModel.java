package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.ExecutionTime;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.ExecutionTimePreference;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;

public class SelectViewModel extends BaseViewModel<SelectNavigator> {

    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> databaseSelectTime;

    private final MutableLiveData<Long> allSelectTime;

    private final MutableLiveData<Long> viewSelectTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public SelectViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.databaseSelectTime = new MutableLiveData<>();
        this.allSelectTime = new MutableLiveData<>();
        this.viewSelectTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    // Method yang digunakan untuk select data
    public void selectDatabase(ExecutionTimePreference executionTimePreference, Long numOfData) {
        AtomicLong viewSelectTime = new AtomicLong(0);
        AtomicLong selectDbTime = new AtomicLong(0);
        AtomicLong selectTime = new AtomicLong(0);
        AtomicLong allSelectTime = new AtomicLong(System.currentTimeMillis());
        AtomicInteger index = new AtomicInteger(0);
        List<Medical> medicals = new ArrayList<>();
        getCompositeDisposable().add(getDataManager()
            //Get All Hospital with Limit
            .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                    //Get All Medicine with same hospital Id
                    .concatMap(hospital -> {
                        selectTime.set(System.currentTimeMillis());
                        return Flowable.zip(
                                getDataManager().getMedicineForHospitalId(hospital.getId()),
                                Flowable.just(hospital),
                                ((medicineList, h) -> {
                                    for (Medicine m : medicineList) {
                                        if (index.get() < numOfData) {
                                            medicals.add(new Medical(h.getName(), m.getName()));
                                            index.getAndIncrement();
                                        }
                                    }
                                    return medicals;
                                })
                        );
                    })
                .doOnNext(medicalList -> {
                    if (!medicalList.isEmpty())
                        selectDbTime.set(selectDbTime.longValue() + (System.currentTimeMillis() - selectTime.longValue()));
                })
            .observeOn(getSchedulerProvider().ui())
            .subscribe(medicalList -> {
                if (medicalList != null && index.get() == numOfData) {
                    this.medicalListLiveData.setValue(medicalList); //Change data list
                    this.numOfRecord.setValue(index.longValue()); //Change number of record
                    this.databaseSelectTime.setValue(selectDbTime.longValue()); //Change execution time
                    AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                    AtomicLong timeElapsed = new AtomicLong(endTime.longValue() - allSelectTime.longValue());
                    viewSelectTime.set(timeElapsed.longValue() - selectDbTime.longValue());
                    this.viewSelectTime.setValue(viewSelectTime.longValue());
                    this.allSelectTime.setValue(timeElapsed.longValue());
                    Log.d("SVM", "selectDatabase: " + index.longValue());
                    index.getAndIncrement();

                    ExecutionTime executionTime = executionTimePreference.getExecutionTime();
                    executionTime.setDatabaseSelectTime(selectDbTime.toString());
                    executionTime.setAllSelectTime(timeElapsed.toString());
                    executionTime.setViewSelectTime(viewSelectTime.toString());
                    executionTime.setNumOfRecordSelect(numOfData.toString());
                    executionTimePreference.setExecutionTime(executionTime);
                }
            }, throwable -> Log.d("SVM", "selectDatabase: " + throwable.getMessage())
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

    public LiveData<Long> getDatabaseSelectTime() {
        return databaseSelectTime;
    }

    public MutableLiveData<Long> getAllSelectTime() {
        return allSelectTime;
    }

    public MutableLiveData<Long> getViewSelectTime() {
        return viewSelectTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}