package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;
//
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
//import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import io.reactivex.Flowable;
//
public class CRUDViewModel extends BaseViewModel<CRUDNavigator> {
//
//    private final MutableLiveData<Long> numOfRecord;
//
//    private final MutableLiveData<Long> executionTime;
//
//    private MutableLiveData<List<Medical>> medicalListLiveData;
//
    public CRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
//        this.numOfRecord = new MutableLiveData<>();
//        this.executionTime = new MutableLiveData<>();
//        this.medicalListLiveData = new MutableLiveData<>();
    }
//
//    public void seedDatabaseMedical() {
//        List<Medical> medicals = new ArrayList<>();
//        getCompositeDisposable().add(getDataManager()
//            .isHospitalEmpty().concatMap(aBoolean -> {
//                if (!aBoolean) {
//                    return getDataManager().getAllHospital()
//                            .subscribeOn(getSchedulerProvider().io())
//                            .concatMap(Flowable::fromIterable)
//                            .observeOn(getSchedulerProvider().ui())
//                            .subscribeOn(getSchedulerProvider().io())
//                            .concatMap(hospital -> Flowable.fromIterable(hospital.getMedicineList())
//                                    .observeOn(getSchedulerProvider().ui())
//                                    .subscribeOn(getSchedulerProvider().io())
//                                    .concatMap(medicine -> {
//                                            medicals.add(new Medical(hospital.getName(),
//                                                    medicine.getName()));
//                                            return Flowable.just(true);
//                                    }));
//                } else
//                    return Flowable.just(false);
//            }).subscribe(aBoolean -> {
//                if (aBoolean) {
//                    this.medicalListLiveData.postValue(medicals);
//                }
//            }, throwable -> {
//                Log.d("CVM", "seedDatabase: " + throwable.getMessage());
//            }));
//    }
//
//    public void insertDatabase(Long numOfData) {
//        setIsLoading(true);
//        long startTime = System.currentTimeMillis();
//        getCompositeDisposable().add(getDataManager()
//                .seedDatabaseHospital(numOfData)
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(aBoolean -> getDataManager().seedDatabaseMedicine(numOfData))
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                        if (aBoolean) {
//                            this.numOfRecord.postValue((numOfData));
//                            long endTime = System.currentTimeMillis();
//                            long timeElapsed = endTime - startTime; //In MilliSeconds
//                            this.executionTime.postValue(timeElapsed); //To MilliSeconds
//                            Log.d("CVM", "insertDatabase: " + numOfData);
//                        } else
//                            Log.d("CVM", "insertDatabase: " + numOfData);
//                        setIsLoading(false);
//                    }
//                , throwable -> {
//                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
//                }
//                )
//        );
//    }
//
//    public void selectDatabase(Long numOfData) {
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger(0);
//        List<Medical> medicals = new ArrayList<>();
//        getCompositeDisposable().add(getDataManager()
//            .getAllHospital()
//            .subscribeOn(getSchedulerProvider().io())
//            .concatMap(hospitalList -> hospitalList != null ? Flowable.fromIterable(hospitalList) : Flowable.fromIterable(new ArrayList<>()))
//                .observeOn(getSchedulerProvider().ui())
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(hospital -> hospital != null ? Flowable.fromIterable(hospital.getMedicineList())
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribeOn(getSchedulerProvider().io())
//                    .concatMap(medicine -> {
//                        if (index.get() < numOfData) {
//                            medicals.add(new Medical(hospital.getName(),
//                                    medicine.getName()));
//                            index.getAndIncrement();
////                            if (index.get() == numOfData)
////                                Log.d("CVM", "selectDatabase: " + index.get());
//                            return Flowable.just(true);
//                        } else
//                            return Flowable.just(false);
//                    }) : Flowable.just(false))
//            .observeOn(getSchedulerProvider().ui())
//            .subscribe(aBoolean -> {
//                if (aBoolean) {
//                    this.medicalListLiveData.postValue(medicals);
//                    this.numOfRecord.postValue(index.longValue());
//                    long endTime = System.currentTimeMillis();
//                    long timeElapsed = endTime - startTime; //In MilliSeconds
//                    this.executionTime.postValue(timeElapsed); //To MilliSeconds
//                } else if (index.get() < numOfData)
//                    Log.d("CVM", "selectDatabase: " + index.get());
//            }, throwable -> {
//                Log.d("CVM", "selectDatabase: " + throwable.getMessage());
//            }));
//    }
//
//    public void updateDatabase(Long numOfData) {
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger(0);
//        List<Medical> medicals = new ArrayList<>();
//        getCompositeDisposable().add(getDataManager()
//                .getAllHospital()
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(hospitalList -> hospitalList != null ? Flowable.fromIterable(hospitalList) : Flowable.fromIterable(new ArrayList<>()))
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .concatMap(hospital -> hospital != null ? Flowable.fromIterable(hospital.getMedicineList())
//                                .observeOn(getSchedulerProvider().ui())
//                                .subscribeOn(getSchedulerProvider().io())
//                                .concatMap(medicine -> {
//                                    if (index.get() < numOfData) {
//                                        medicine.setName(medicine.getName() + " NEW");
//                                        medicals.add(new Medical(hospital.getName(), medicine.getName()));
//                                        index.getAndIncrement();
////                                        if (index.get() == numOfData)
//                                            Log.d("CVM", "updateDatabase: " + index.get());
//                                        return getDataManager().updateDatabaseMedicine(medicine);
//                                    } else
//                                        return Flowable.just(false);
//                                }) : Flowable.just(false)
//                )
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                    if (aBoolean) {
//                        this.medicalListLiveData.postValue(medicals);
//                        this.numOfRecord.postValue(index.longValue());
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime; //In MilliSeconds
//                        this.executionTime.postValue(timeElapsed); //To MilliSeconds
//                    } else if (index.get() < numOfData)
//                        Log.d("CVM", "updateDatabase: " + index.get());
//                }, throwable -> {
//                    Log.d("CVM", "updateDatabase: " + throwable.getMessage());
//                }));
//    }
//
//    public void deleteDatabase(Long numOfData) {
//        long startTime = System.currentTimeMillis();
//        AtomicInteger index = new AtomicInteger(numOfData.intValue()-1);
//        List<Medical> medicals = new ArrayList<>();
//        getCompositeDisposable().add(getDataManager()
//                .getAllHospital()
//                .subscribeOn(getSchedulerProvider().io())
//                .concatMap(hospitalList -> hospitalList != null ? Flowable.fromIterable(hospitalList) : Flowable.fromIterable(new ArrayList<>()))
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribeOn(getSchedulerProvider().io())
//                    .concatMap(hospital -> hospital != null ? Flowable.fromIterable(hospital.getMedicineList())
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .concatMap(medicine -> {
//                            if (index.get() >= 0) {
//                                index.getAndDecrement();
////                                if (index.get() < 0)
//                                    Log.d("CVM", "deleteDatabase: " + index.get());
//                                return getDataManager().deleteDatabaseMedicine(medicine);
//                            } else
//                                medicals.add(new Medical(hospital.getName(), medicine.getName()));
//                                return Flowable.just(false);
//                        }) : Flowable.just(false)
//                )
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(aBoolean -> {
//                    if (aBoolean) {
//                        this.numOfRecord.postValue(getDataManager().getAllMedicine().blockingFirst().size()-index.longValue());
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime; //In MilliSeconds
//                        this.executionTime.postValue(timeElapsed); //To MilliSeconds
//                    } else if (index.get() < 0)
//                        Log.d("CVM", "deleteDatabase: " + index.get());
//                }, throwable -> {
//                    Log.d("CVM", "deleteDatabase: " + throwable.getMessage());
//                }));
//    }
//
//    public void setMedicalListLiveData(MutableLiveData<List<Medical>> medicalListLiveData) {
//        this.medicalListLiveData = medicalListLiveData;
//    }
//
//    public LiveData<List<Medical>> getMedicalListLiveData() {
//        return medicalListLiveData;
//    }
//
//    public LiveData<Long> getNumOfRecord() {
//        return numOfRecord;
//    }
//
//    public LiveData<Long> getExecutionTime() {
//        return executionTime;
//    }
//
//    public void onClick() {
//        getNavigator().onClick();
//    }
}