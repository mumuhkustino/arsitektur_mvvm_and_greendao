package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.List;

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

    public void fetchMedicals(Long numOfData) {
        setIsLoading(true);
        long startTime = System.nanoTime();
        executionTime.setValue(startTime);
        getCompositeDisposable().add(getDataManager()
            .getMedical(numOfData)
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(medicalList -> {
                if (medicalList != null && !medicalList.isEmpty()) {
                    numOfRecord.setValue((long) medicalList.size());
                    long endTime = System.nanoTime();
                    long timeElapsed = endTime - startTime; //In NanoSeconds
                    executionTime.setValue(timeElapsed/1000000); //To MilliSeconds
                    medicalListLiveData.setValue(medicalList);
                    Log.d("IVM", "fetchMedicals: " + medicalList.size());
                }
                setIsLoading(false);
            }, throwable -> {
                setIsLoading(false);
                getNavigator().handleError(throwable);
            }
        ));
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

    public void onItemClick() {
        getNavigator().onItemClick();
    }
}
