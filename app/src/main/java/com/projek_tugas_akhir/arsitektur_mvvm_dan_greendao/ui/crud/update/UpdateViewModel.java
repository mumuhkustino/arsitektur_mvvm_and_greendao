package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update;

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

public class UpdateViewModel extends BaseViewModel<UpdateNavigator> {

    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> executionTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public UpdateViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    public void updateDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        AtomicInteger index = new AtomicInteger(0);
        List<Medical> medicals = new ArrayList<>();
        getCompositeDisposable().add(getDataManager()
            .getAllHospital(numOfData > 1000 ? numOfData / 1000 : numOfData)
                .concatMap(hospitalList -> hospitalList != null ? Flowable.fromIterable(hospitalList)
                        : Flowable.fromIterable(new ArrayList<>()))
                    .concatMap(hospital -> hospital != null ? getDataManager().getMedicineForHospitalId(hospital.getId())
                        .concatMap(medicineList -> medicineList != null ? Flowable.fromIterable(medicineList)
                                :Flowable.fromIterable(new ArrayList<>()))
                                    .concatMap(medicine -> {
                                        if (index.get() < numOfData) {
                                            medicine.setName(medicine.getName() + " NEW");
                                            medicals.add(new Medical(hospital.getName(), medicine.getName()));
                                            index.getAndIncrement();
                                            try {
                                                return getDataManager().updateDatabaseMedicine(medicine)
                                                        .concatMap(Flowable::just);
                                            } catch (Exception e) {
                                                return Flowable.just(false);
                                            }
                                        } else
                                            return Flowable.just(false);
                                    }) : Flowable.just(false))
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        this.medicalListLiveData.setValue(medicals);
                        this.numOfRecord.setValue(index.longValue());
                        long endTime = System.currentTimeMillis();
                        long timeElapsed = endTime - startTime; //In MilliSeconds
                        this.executionTime.setValue(timeElapsed); //To MilliSeconds
                    } else if (index.get() == numOfData) {
                        Log.d("CVM", "updateDatabase: " + index.get());
                        index.getAndIncrement();
                    }
                }, throwable -> Log.d("CVM", "updateDatabase: " + throwable.getMessage())));
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