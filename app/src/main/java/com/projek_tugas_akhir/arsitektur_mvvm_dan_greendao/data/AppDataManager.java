package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import android.content.Context;

import com.github.javafaker.Faker;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Disease;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Symptom;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context context;
    private final DbHelper dbHelper;

    @Inject
    public AppDataManager(Context context,
            DbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }


    @Override
    public Observable<Boolean> seedDatabaseHospital() {
        Faker faker = new Faker();
        return dbHelper.isHospitalEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            List<Hospital> hospitalList = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                Hospital hospital = new Hospital();
                                hospital.setName(faker.medical().hospitalName());
                                hospitalList.add(hospital);
                            }
                            return saveHospitalList(hospitalList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseMedicine() {
        Faker faker = new Faker();
        return dbHelper.isMedicineEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            List<Medicine> medicineList = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                Medicine medicine = new Medicine();
                                medicine.setName(faker.medical().medicineName());
                                medicineList.add(medicine);
                            }
                            return saveMedicineList(medicineList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseDisease() {
        Faker faker = new Faker();
        return dbHelper.isDiseaseEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            List<Disease> diseaseList = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                Disease disease = new Disease();
                                disease.setName(faker.medical().diseaseName());
                                diseaseList.add(disease);
                            }
                            return saveDiseaseList(diseaseList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseSymptom() {
        Faker faker = new Faker();
        return dbHelper.isSymptomEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            List<Symptom> symptomList = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                Symptom symptom = new Symptom();
                                symptom.setName(faker.medical().symptoms());
                                symptomList.add(symptom);
                            }
                            return saveSymptomList(symptomList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Long> insertHospital(Hospital hospital) {
        return dbHelper.insertHospital(hospital);
    }

    @Override
    public Observable<Long> insertMedicine(Medicine medicine) {
        return dbHelper.insertMedicine(medicine);
    }

    @Override
    public Observable<Long> insertDisease(Disease disease) {
        return dbHelper.insertDisease(disease);
    }

    @Override
    public Observable<Long> insertSymptom(Symptom symptom) {
        return dbHelper.insertSymptom(symptom);
    }

    @Override
    public Observable<List<Hospital>> getAllHospital() {
        return dbHelper.getAllHospital();
    }

    @Override
    public Observable<List<Medicine>> getAllMedicine() {
        return dbHelper.getAllMedicine();
    }

    @Override
    public Observable<List<Disease>> getAllDisease() {
        return dbHelper.getAllDisease();
    }

    @Override
    public Observable<List<Symptom>> getAllSymptom() {
        return dbHelper.getAllSymptom();
    }

    @Override
    public Observable<Boolean> isHospitalEmpty() {
        return dbHelper.isHospitalEmpty();
    }

    @Override
    public Observable<Boolean> isMedicineEmpty() {
        return dbHelper.isMedicineEmpty();
    }

    @Override
    public Observable<Boolean> isDiseaseEmpty() {
        return dbHelper.isDiseaseEmpty();
    }

    @Override
    public Observable<Boolean> isSymptomEmpty() {
        return dbHelper.isSymptomEmpty();
    }

    @Override
    public Observable<Boolean> saveHospital(Hospital hospital) {
        return dbHelper.saveHospital(hospital);
    }

    @Override
    public Observable<Boolean> saveMedicine(Medicine medicine) {
        return dbHelper.saveMedicine(medicine);
    }

    @Override
    public Observable<Boolean> saveDisease(Disease disease) {
        return dbHelper.saveDisease(disease);
    }

    @Override
    public Observable<Boolean> saveSymptom(Symptom symptom) {
        return dbHelper.saveSymptom(symptom);
    }

    @Override
    public Observable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
        return dbHelper.saveHospitalList(hospitalList);
    }

    @Override
    public Observable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        return dbHelper.saveMedicineList(medicineList);
    }

    @Override
    public Observable<Boolean> saveDiseaseList(List<Disease> diseaseList) {
        return dbHelper.saveDiseaseList(diseaseList);
    }

    @Override
    public Observable<Boolean> saveSymptomList(List<Symptom> symptomList) {
        return dbHelper.saveSymptomList(symptomList);
    }
}
