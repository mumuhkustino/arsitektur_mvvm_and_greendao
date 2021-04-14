package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import android.content.Context;
import android.util.Log;

import com.github.javafaker.Faker;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Disease;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Symptom;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

//    @Override
//    public Observable<List<Medical>> getMedicalData() {
//        return dbHelper.getAllHospital()
//                .flatMap(hospitals -> Observable.fromIterable(hospitals))
//                .flatMap(hospital -> Observable.zip(
//                    dbHelper.getMedicineForHospitalId(hospital.getId()),
//                    Observable.just(hospital),
//                    (medicine, hospital1) -> new Medical(hospital1.getName(), medicine.get(0).getName(), "Disease", "Symptom")))
//                .toList()
//                .toObservable();
//        Observable<List<Hospital>> hospitals = dbHelper.getAllHospital().flatMap(hospitalList -> Observable.fromIterable(hospitalList));
//        Observable<List<Medicine>> medicines = dbHelper.getMedicineForHospitalId();
//        return dbHelper.getAllHospital()
//                .flatMap(hospitals -> Observable.fromIterable(hospitals))
//                .flatMap(hospital -> dbHelper.getMedicineForHospitalId(hospital.getId()))
//                .flatMap(medicines -> Observable.fromIterable(medicines))
//                .flatMap(medicine -> dbHelper.getDiseaseForHospitalId(hospital.getId()))
//                .toList()
//                .toObservable();
//        Observable<List<Hospital>> hospitalsObservable = dbHelper.getAllHospital();
//        Observable<List<Medicine>> medicinesObservable = dbHelper.getAllMedicine();
//        Observable<List<Disease>> diseasesObservable = dbHelper.getAllDisease();
//        Observable<List<Symptom>> symptomsObservable = dbHelper.getAllSymptom();
//        Observable<List<Medical>> medicalsObservable = hospitalsObservable
//                .flatMap((hospitals) -> Observable.fromIterable(hospitals)
//                .flatMap(hospital -> medicinesObservable
//                    .flatMap((medicines) -> Observable.fromIterable(medicines)
//                    .flatMap(medicine -> diseasesObservable
//                        .flatMap(diseases -> Observable.fromIterable(diseases)
//                        .flatMap(disease -> symptomsObservable
//                        .flatMap((symptoms) -> Observable.fromIterable(symptoms)
//                            .map((symptom) -> new Medical(hospital.getName(), medicine.getName(), disease.getName(), symptom.getName()))
//                        )))
//                    ))
//                )).toList().toObservable();
//        Disposable medicalDisposable = medicalsObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(medical -> Log.d(TAG, "apply: " + medical.size()));
//        return medicalsObservable;
//    }

    @Override
    public Single<List<Medical>> getMedical() {
        Observable<List<Hospital>> hospitalsObservable = dbHelper.getAllHospital();
        Observable<List<Medicine>> medicinesObservable = dbHelper.getAllMedicine();
        Observable<List<Disease>> diseasesObservable = dbHelper.getAllDisease();
        Observable<List<Symptom>> symptomsObservable = dbHelper.getAllSymptom();
        Single<List<Medical>> medicalsObservable = hospitalsObservable
                .flatMap((hospitals) -> Observable.fromIterable(hospitals)
                        .flatMap(hospital -> medicinesObservable
                                .flatMap((medicines) -> Observable.fromIterable(medicines)
                                        .flatMap(medicine -> diseasesObservable
                                                .flatMap(diseases -> Observable.fromIterable(diseases)
                                                        .flatMap(disease -> symptomsObservable
                                                                .flatMap((symptoms) -> Observable.fromIterable(symptoms)
                                                                        .map((symptom) -> new Medical(hospital.getName(), medicine.getName(), disease.getName(), symptom.getName()))
                                                                )))
                                        ))
                        )).toList();
        return medicalsObservable;
    }

    @Override
    public Single<List<Medical>> getMedical(Long numOfData) {
//    public Single<List<Medical>> getMedical() {
        Observable<List<Hospital>> hospitalsObservable = dbHelper.getAllHospital();
        Observable<List<Medicine>> medicinesObservable = dbHelper.getAllMedicine();
        Observable<List<Disease>> diseasesObservable = dbHelper.getAllDisease();
        Observable<List<Symptom>> symptomsObservable = dbHelper.getAllSymptom();
        Single<List<Medical>> medicalsObservable = hospitalsObservable
                .flatMap((hospitals) -> Observable.fromIterable(hospitals)
                        .flatMap(hospital -> medicinesObservable
                                .flatMap((medicines) -> Observable.fromIterable(medicines)
                                        .flatMap(medicine -> diseasesObservable
                                                .flatMap(diseases -> Observable.fromIterable(diseases)
                                                        .flatMap(disease -> symptomsObservable
                                                                .flatMap((symptoms) -> Observable.fromIterable(symptoms)
                                                                        .map((symptom) -> new Medical(hospital.getName(), medicine.getName(), disease.getName(), symptom.getName()))
                                                                )))
                                        ))
                        )).toList();
        List<Medical> medicals = new ArrayList<>();
        List<Medical> tempMedicals = medicalsObservable.blockingGet();
        for (int i = 0; i < numOfData; i++) {
            medicals.add(tempMedicals.get(i));
        }
        Log.d(TAG, "getMedicals: " + medicals.size());
        Disposable medicalDisposable = medicalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe(medical -> Log.d(TAG, "getMedical: " + medical.size()));
        return Single.just(medicals);
//        return medicalsObservable;
    }

    @Override
    public Observable<Boolean> seedDatabaseHospital() {
        Faker faker = new Faker();
        return dbHelper.isHospitalEmpty()
                .concatMap(aBoolean -> {
                    if (aBoolean) {
                        List<Hospital> hospitalList = new ArrayList<>();
//                        for (int i = 0; i < 1021; i++) {
                        for (int i = 0; i < 10; i++) {
                            Hospital hospital = new Hospital();
                            hospital.setName(faker.medical().hospitalName());
                            hospitalList.add(hospital);
                        }
                        return saveHospitalList(hospitalList);
                    }
                    return Observable.just(false);
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseHospital(Long numOfData) {
        Faker faker = new Faker();
        List<Hospital> hospitalList = dbHelper.getAllHospital().blockingSingle();
        if (hospitalList.size() > 100) {
            return saveHospitalList(hospitalList);
        } else {
            int n = 100 - hospitalList.size();
            if (numOfData < n)
                n = numOfData.intValue();
            for (int i = 0; i < n; i++) {
                Hospital hospital = new Hospital();
                hospital.setName(faker.medical().hospitalName());
                hospitalList.add(hospital);
            }
//        return Observable.just(dbHelper.getAllHospital().blockingSingle().addAll(hospitalList));
            return saveHospitalList(hospitalList);
//        return dbHelper.isHospitalEmpty()
//                .concatMap(aBoolean -> {
//                    if (aBoolean) {
//
//                    }
//                    return Observable.just(false);
//                });
        }
    }

    @Override
    public Observable<Boolean> seedDatabaseMedicine() {
        Faker faker = new Faker();
        return dbHelper.isMedicineEmpty()
                .concatMap(aBoolean -> {
                    if (aBoolean) {
                        List<Medicine> medicineList = new ArrayList<>();
//                        for (int i = 0; i < 1082; i++) {
                        for (int i = 0; i < 10; i++) {
                            Medicine medicine = new Medicine();
                            medicine.setName(faker.medical().medicineName());
                            medicineList.add(medicine);
                        }
                        return saveMedicineList(medicineList);
                    }
                    return Observable.just(false);
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseMedicine(Long numOfData) {
        Faker faker = new Faker();
        List<Medicine> medicineList = dbHelper.getAllMedicine().blockingSingle();
        if (medicineList.size() > 100) {
            return saveMedicineList(medicineList);
        } else {
            int n = 100 - medicineList.size();
            if (numOfData < n)
                n = numOfData.intValue();
            for (int i = 0; i < n; i++) {
                Medicine medicine = new Medicine();
                medicine.setName(faker.medical().medicineName());
                medicineList.add(medicine);
            }
//        return Observable.just(dbHelper.getAllMedicine().blockingSingle().addAll(medicineList));
            return saveMedicineList(medicineList);
        }
    }

    @Override
    public Observable<Boolean> seedDatabaseDisease() {
        Faker faker = new Faker();
        return dbHelper.isDiseaseEmpty()
            .concatMap(aBoolean -> {
                if (aBoolean) {
                    List<Disease> diseaseList = new ArrayList<>();
//                    for (int i = 0; i < 50; i++) {
                    for (int i = 0; i < 10; i++) {
                        Disease disease = new Disease();
                        disease.setName(faker.medical().diseaseName());
                        diseaseList.add(disease);
                    }
                    return saveDiseaseList(diseaseList);
                }
                return Observable.just(false);
            });
    }

    @Override
    public Observable<Boolean> seedDatabaseDisease(Long numOfData) {
        Faker faker = new Faker();
        List<Disease> diseaseList = dbHelper.getAllDisease().blockingSingle();
        if (diseaseList.size() > 10) {
            return saveDiseaseList(diseaseList);
        } else {
            int n = 10 - diseaseList.size();
            if (numOfData < n)
                n = numOfData.intValue();
            for (int i = 0; i < n; i++) {
                Disease disease = new Disease();
                disease.setName(faker.medical().diseaseName());
                diseaseList.add(disease);
            }
//        return Observable.just(dbHelper.getAllDisease().blockingSingle().addAll(diseaseList));
            return saveDiseaseList(diseaseList);
        }
    }

    @Override
    public Observable<Boolean> seedDatabaseSymptom() {
        Faker faker = new Faker();
        return dbHelper.isSymptomEmpty()
            .concatMap(aBoolean -> {
                if (aBoolean) {
                    List<Symptom> symptomList = new ArrayList<>();
//                    for (int i = 0; i < 129; i++) {
                    for (int i = 0; i < 10; i++) {
                        Symptom symptom = new Symptom();
                        symptom.setName(faker.medical().symptoms());
                        symptomList.add(symptom);
                    }
                    return saveSymptomList(symptomList);
                }
                return Observable.just(false);
            });
    }

    @Override
    public Observable<Boolean> seedDatabaseSymptom(Long numOfData) {
        Faker faker = new Faker();
        List<Symptom> symptomList = dbHelper.getAllSymptom().blockingSingle();
        if (symptomList.size() >= 10) {
            return saveSymptomList(symptomList);
        } else {
            int n = 10 - symptomList.size();
            if (numOfData < n)
                n = numOfData.intValue();
            for (int i = 0; i < n; i++) {
                Symptom symptom = new Symptom();
                symptom.setName(faker.medical().symptoms());
                symptomList.add(symptom);
            }
//        return Observable.just(dbHelper.getAllSymptom().blockingSingle().addAll(symptomList));
            return saveSymptomList(symptomList);
        }
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
    public Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return dbHelper.getMedicineForHospitalId(hospitalId);
    }

    @Override
    public Observable<List<Disease>> getDiseaseForHospitalId(Long hospitalId) {
        return dbHelper.getDiseaseForHospitalId(hospitalId);
    }

    @Override
    public Observable<List<Symptom>> getSymptomForDiseaseId(Long diseaseId) {
        return dbHelper.getSymptomForDiseaseId(diseaseId);
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
        Log.d(TAG, "saveHospitalList: " + hospitalList.size());
        return dbHelper.saveHospitalList(hospitalList);
    }

    @Override
    public Observable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        Log.d(TAG, "saveMedicineList: " + medicineList.size());
        return dbHelper.saveMedicineList(medicineList);
    }

    @Override
    public Observable<Boolean> saveDiseaseList(List<Disease> diseaseList) {
        Log.d(TAG, "saveDiseaseList: " + diseaseList.size());
        return dbHelper.saveDiseaseList(diseaseList);
    }

    @Override
    public Observable<Boolean> saveSymptomList(List<Symptom> symptomList) {
        Log.d(TAG, "saveSymptomList: " + symptomList.size());
        return dbHelper.saveSymptomList(symptomList);
    }
}
