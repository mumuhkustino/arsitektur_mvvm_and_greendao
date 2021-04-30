package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;

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

    public void refreshMedical(Long numOfData, List<Hospital> hospitalList) {
        this.medicalListLiveData.setValue(new ArrayList<>());
        Long index = (long) 0;
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
//                Log.d("CVM", "refreshMedical = " + index);
            }
//            if (index >= numOfData) break;
        }
        Log.d("CVM", "refreshMedical = " + size);
        this.medicalListLiveData.setValue(medicals);
    }

    public void selectDatabase(Long numOfData) {
        setIsLoading(true);
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
            .getAllHospital()
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .toFlowable(BackpressureStrategy.DROP)
            .subscribe(hospitalList -> {
                if (hospitalList != null) {
                    refreshMedical(numOfData, hospitalList);
                    this.numOfRecord.setValue(numOfData);
                    long endTime = System.currentTimeMillis();
                    long timeElapsed = endTime - startTime; //In MilliSeconds
                    this.executionTime.setValue(timeElapsed); //To MilliSeconds
//                    Log.d("CVM", "selectDatabase: " + hospitalList.size());
                }
                setIsLoading(false);
            }, throwable -> {
                Log.d("CVM", "selectDatabase: " + throwable.getMessage());
                setIsLoading(false);
            }));
    }

    public void updateMedical(Long numOfData, List<Hospital> hospitalList) {
        this.medicalListLiveData.setValue(new ArrayList<>());
        Long index = (long) 0;
        int size = 0;
        List<Medical> medicals = new ArrayList<>();
        for (int i = 0; i < hospitalList.size(); i++) {
            if (hospitalList.get(i) != null && hospitalList.get(i)
                    .getMedicineList() != null) {
                int j = 0;
                size += hospitalList.get(i).getMedicineList().size();
                while (j < hospitalList.get(i).getMedicineList().size()) {
                    if (index < numOfData) {
                        hospitalList.get(i).getMedicineList().get(j)
                                .setName(hospitalList.get(i).getMedicineList().get(j).getName() + " NEW");
                        getCompositeDisposable().add(getDataManager()
                                .updateDatabaseMedicine(hospitalList.get(i)
                                        .getMedicineList()
                                        .get(j))
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())
                                .toFlowable(BackpressureStrategy.DROP)
                                .subscribe(aBoolean -> {
//                                    if (aBoolean) {
//                                        Log.d("CVM", "updateMedical: " + aBoolean);
//                                    }
                                }, throwable -> {
                                    Log.d("CVM", "updateMedical: " + throwable.getMessage());
                                }));
                        index++;
                    }
                    medicals.add(new Medical(hospitalList.get(i).getName(),
                            hospitalList.get(i).getMedicineList().get(j).getName()));
                    j++;
                }
//                Log.d("CVM", "refreshMedical = " + index);
            }
//            if (index >= numOfData) break;
        }
        Log.d("CVM", "updateMedical = " + size);
//        getCompositeDisposable().add(getDataManager()
//                .saveHospitalList(hospitalList)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .toFlowable(BackpressureStrategy.DROP)
//                .subscribe(aBoolean -> {
////                    if (aBoolean) {
////                        Log.d("CVM", "updateMedical: " + aBoolean);
////                    }
//                }, throwable -> {
//                    Log.d("CVM", "updateMedical: " + throwable.getMessage());
//                }));
        this.medicalListLiveData.setValue(medicals);
    }

    public void updateDatabase(Long numOfData) {
        setIsLoading(true);
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
                .getAllHospital()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toFlowable(BackpressureStrategy.DROP)
                .subscribe(hospitalList -> {
                    if (hospitalList != null) {
//                        getCompositeDisposable().add(getDataManager()
//                                .saveHospitalList(
                                        updateMedical(numOfData, hospitalList);
//                                )
//                                .subscribeOn(getSchedulerProvider().io())
//                                .observeOn(getSchedulerProvider().ui())
//                                .toFlowable(BackpressureStrategy.DROP)
//                                .subscribe(aBoolean -> {
//                                    if (aBoolean) {
                                        this.numOfRecord.setValue(numOfData);
                                        long endTime = System.currentTimeMillis();
                                        long timeElapsed = endTime - startTime; //In MilliSeconds
                                        this.executionTime.setValue(timeElapsed); //To MilliSeconds
//                                Log.d("CVM", "updateMedical: " + aBoolean);
//                                    }
//                                }, throwable -> {
//                                    Log.d("CVM", "updateMedical: " + throwable.getMessage());
//                                }));
//                        Log.d("CVM", "updateDatabase: " + hospitalList.size());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    Log.d("CVM", "updateDatabase: " + throwable.getMessage());
                    setIsLoading(false);
                }));
    }

    public void deleteMedical(Long numOfData, List<Hospital> hospitalList) {
        this.medicalListLiveData.setValue(new ArrayList<>());
        Long index = (long) 0;
        int size = 0;
        List<Medical> medicals = new ArrayList<>();
        for (int i = 0; i < hospitalList.size(); i++) {
            if (hospitalList.get(i) != null
                    && hospitalList.get(i).getMedicineList() != null) {
                int j = 0;
                size += hospitalList.get(i).getMedicineList().size();
//                if (numOfData > hospitalList.get(i).getMedicineList().size()) {
//                    Log.d("CVM", "deleteMedical: remove Hospital");
//                    boolean success = hospitalList.remove(hospitalList.get(i));
//                    Log.d("CVM", "deleteMedical 1 is " + success);
//                } else {
                    while (j < hospitalList.get(i).getMedicineList().size()) {
//                        boolean success = false;
                        if (index < numOfData) {
//                            Log.d("CVM", "deleteMedical: remove Medicine " + index);
//                            success =
                            Medicine med = hospitalList.get(i).getMedicineList()
                                .remove(j);
                            getCompositeDisposable().add(getDataManager()
                                    .loadMedicine(med)
                                    .subscribeOn(getSchedulerProvider().io())
                                    .observeOn(getSchedulerProvider().ui())
                                    .concatMap(medicine -> {
                                        if (medicine != null)
                                            return getDataManager().deleteMedicine(medicine);
                                        return Observable.just(false);
                                        })
                                    .toFlowable(BackpressureStrategy.DROP)
                                    .subscribe(aBoolean -> {
                                        if (aBoolean) {
//                                            Log.d("CVM", "deleteMedical 2 is " + aBoolean);
                                        }
                                    }, throwable -> {
                                        Log.d("CVM", "deleteMedical: " + throwable.getMessage());
                                    }));
//                            medicals.add(new Medical(hospitalList.get(i).getName(),
//                                    hospitalList.get(i).getMedicineList().get(j).getName()));
                            index++;
                        } else {
//                            Log.d("CVM", "deleteMedical: add Medical " + index);
                            medicals.add(new Medical(hospitalList.get(i).getName(),
                                    hospitalList.get(i).getMedicineList().get(j).getName()));
//                            index++;
                            j++;
                        }
//                        if (success)
//                            index++;

                    }
//                }
            }
//            if (index >= numOfData) break;
        }
        Log.d("CVM", "deleteMedical = " + size);
//        getCompositeDisposable().add(getDataManager()
//                .saveHospitalList(hospitalList)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .toFlowable(BackpressureStrategy.DROP)
//                .subscribe(aBoolean -> {
//                    if (aBoolean) {
//                        Log.d("CVM", "deleteMedical: " + aBoolean);
//                    }
//                }, throwable -> {
//                    Log.d("CVM", "deleteMedical: " + throwable.getMessage());
//                }));
        this.medicalListLiveData.setValue(medicals);
    }


    public void deleteDatabase(Long numOfData) {
//        Log.d("CVM", "deleteDatabase: Mulai ");
////        Long num = Math.round(Math.pow(numOfData, 0.25));
        setIsLoading(true);
        long startTime = System.currentTimeMillis();
////        executionTime.setValue(startTime);
        getCompositeDisposable().add(getDataManager()
                .getAllHospital()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .toFlowable(BackpressureStrategy.DROP)
                .subscribe(hospitalList -> {
                    if (hospitalList != null) {
//                        getCompositeDisposable().add(getDataManager()
//                                .saveHospitalList(
                                        deleteMedical(numOfData, hospitalList);
//                                )
//                                .subscribeOn(getSchedulerProvider().io())
//                                .observeOn(getSchedulerProvider().ui())
//                                .subscribe(aBoolean -> {
//                                    if (aBoolean) {
                                        this.numOfRecord.setValue(numOfData);
                                        long endTime = System.currentTimeMillis();
                                        long timeElapsed = endTime - startTime; //In MilliSeconds
                                        this.executionTime.setValue(timeElapsed); //To MilliSeconds
//                                        Log.d("CVM", "deleteDatabase: " + hospitalList.size());
//                                    }
//                                }));
                    }
                    setIsLoading(false);
                }, throwable -> {
                    Log.d("CVM", "deleteDatabase: " + throwable.getMessage());
                    setIsLoading(false);
                }));
    }

    public void insertDatabase(Long numOfData) {
//        Long numHospital, numMedicine;
//        if (numOfData < 100000) {
//            numHospital = (long) 10;
//            numMedicine = (long) 1000;
//        } else if (numOfData < 500000) {
//            numHospital = (long) 100;
//            numMedicine = (long) 1000;
//        } else if (numOfData < 1000000) {
//            numHospital = (long) 500;
//            numMedicine = (long) 1000;
//        } else {
//            numHospital = (long) 1000;
//            numMedicine = (long) 1000;
//        }
        setIsLoading(true);
        long startTime = System.currentTimeMillis();
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital(numOfData)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .concatMap(aBoolean -> getDataManager().seedDatabaseMedicine(numOfData))
                .toFlowable(BackpressureStrategy.DROP)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        this.numOfRecord.setValue((numOfData));
                        long endTime = System.currentTimeMillis();
                        long timeElapsed = endTime - startTime; //In MilliSeconds
                        this.executionTime.setValue(timeElapsed); //To MilliSeconds
                        Log.d("CVM", "insertDatabase: " + numOfData);
                    }
                    setIsLoading(false);
                }
//                , throwable -> {
//                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
//                }
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

    public void onClick() {
        getNavigator().onClick();
    }
}