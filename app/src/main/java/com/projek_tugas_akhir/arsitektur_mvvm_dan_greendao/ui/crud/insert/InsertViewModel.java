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

    private final MutableLiveData<List<Medical>> medicalListLiveData;

    public InsertViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
        fetchMedicals();
    }

    public void fetchMedicals() {
        setIsLoading(true);
        long startTime = System.nanoTime();
        executionTime.setValue(startTime);
        getCompositeDisposable().add(getDataManager()
            .getMedical()
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

    public LiveData<List<Medical>> getMedicalListLiveData() {
        return medicalListLiveData;
    }

    public MutableLiveData<Long> getNumOfRecord() {
        return numOfRecord;
    }

    public String getRecord() {
        return String.valueOf("RECORD : ").concat(numOfRecord.getValue().toString());
    }

    public MutableLiveData<Long> getExecutionTime() {
        return executionTime;
    }

    public String getTime() {
        return String.valueOf("RECORD : ").concat(executionTime.getValue().toString());
    }
}
