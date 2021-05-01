package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import android.content.Context;
import android.util.Log;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.AppConstants;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

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

//    @Override
//    public Single<List<Medical>> getMedical() {
//        Observable<List<Hospital>> hospitalsObservable = dbHelper.getAllHospital();
//        Observable<List<Medicine>> medicinesObservable = dbHelper.getAllMedicine();
////        Observable<List<Disease>> diseasesObservable = dbHelper.getAllDisease();
////        Observable<List<Symptom>> symptomsObservable = dbHelper.getAllSymptom();
//        Single<List<Medical>> medicalsObservable = hospitalsObservable
//                .flatMap((hospitals) -> Observable.fromIterable(hospitals)
//                        .flatMap(hospital -> medicinesObservable
//                                .flatMap((medicines) -> Observable.fromIterable(medicines)
////                                        .flatMap(medicine -> diseasesObservable
////                                                .flatMap(diseases -> Observable.fromIterable(diseases)
////                                                        .flatMap(disease -> symptomsObservable
////                                                                .flatMap((symptoms) -> Observable.fromIterable(symptoms)
////                                                                        .map((symptom) -> new Medical(hospital.getName(), medicine.getName(), disease.getName(), symptom.getName()))
//                                                .map((medicine) -> new Medical(hospital.getName(), medicine.getName()))
//                                                                )
////                        ))
////                                        ))
//                        )).toList();
//        return medicalsObservable;
//    }
//
//    @Override
//    public Single<List<Medical>> getMedical(Long numOfData) {
////    public Single<List<Medical>> getMedical() {
//        Observable<List<Hospital>> hospitalsObservable = dbHelper.getAllHospital();
//        Observable<List<Medicine>> medicinesObservable = dbHelper.getAllMedicine();
////        Observable<List<Disease>> diseasesObservable = dbHelper.getAllDisease();
////        Observable<List<Symptom>> symptomsObservable = dbHelper.getAllSymptom();
//        Single<List<Medical>> medicalsObservable = hospitalsObservable
//                .flatMap((hospitals) -> Observable.fromIterable(hospitals)
//                        .flatMap(hospital -> medicinesObservable
//                                .flatMap((medicines) -> Observable.fromIterable(medicines)
////                                        .flatMap(medicine -> diseasesObservable
////                                                .flatMap(diseases -> Observable.fromIterable(diseases)
////                                                        .flatMap(disease -> symptomsObservable
////                                                                .flatMap((symptoms) -> Observable.fromIterable(symptoms)
////                                                                        .map((symptom) -> new Medical(hospital.getName(), medicine.getName(), disease.getName(), symptom.getName()))
//                                                .map((medicine) -> new Medical(hospital.getName(), medicine.getName()))
//                                                                )
////                        ))
////                                        ))
//                        )).toList();
//        List<Medical> medicals = new ArrayList<>();
//        List<Medical> tempMedicals = medicalsObservable.blockingGet();
//        for (int i = 0; i < numOfData; i++) {
//            medicals.add(tempMedicals.get(i));
//        }
//        Log.d(TAG, "getMedicals: " + medicals.size());
////        Disposable medicalDisposable = medicalsObservable
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .toFlowable()
////                .subscribe(medical -> Log.d(TAG, "getMedical: " + medical.size()));
//        return Single.just(medicals);
////        return medicalsObservable;
//    }

//    @Override
////    public Observable<Boolean> seedDatabaseHospital() {
////        Faker faker = new Faker();
////        return dbHelper.isHospitalEmpty()
////                .concatMap(aBoolean -> {
////                    if (aBoolean) {
//////                        List<Hospital> hospitalList = new ArrayList<>();
//////                        for (int i = 0; i < 1021; i++) {
////                        for (int i = 0; i < 10; i++) {
////                            Hospital hospital = new Hospital();
////                            hospital.setName(faker.medical().hospitalName());
////                            return saveHospital(hospital);
//////                            hospitalList.add(hospital);
////                        }
//////                        return saveHospitalList(hospitalList);
////                    }
////                    return Observable.just(false);
////                });
////    }

    @Override
    public Observable<Boolean> seedDatabaseHospital(Long numOfData) {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        Long numHospital;
        String pathJson;
        if (numOfData < 100000) {
            numHospital = (long) 10;
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_10;
        } else if (numOfData < 500000) {
            numHospital = (long) 100;
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_100;
        } else if (numOfData < 1000000) {
            numHospital = (long) 500;
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_500;
        } else {
            numHospital = (long) 1000;
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_1000;
        }
        return dbHelper.getAllHospital().count()
                .toObservable()
                .concatMap(aLong -> {
                    if (aLong < numHospital) {
                        Type type = $Gson$Types
                                .newParameterizedTypeWithOwner(null, List.class,
                                        Hospital.class);
                        List<Hospital> hospitalList = gson.fromJson(
                                CommonUtils.loadJSONFromAsset(context,
                                        pathJson),
                                type);
                        return saveHospitalList(hospitalList);
                    }
                    return Observable.just(false);
                });
    }

    @Override
    public Observable<Boolean> updateDatabaseHospital(Hospital hospital) {
//        if (numOfData >= 10000) {
//            numOfData /= 1000;
//        }
//        if (numOfData >= 100) {
//            numOfData = (long) 100;
//        } else {
//            numOfData = (long) 10;
//        }
//        List<Hospital> hospitalList = dbHelper.getAllHospital().blockingSingle();
//        if (numOfData > hospitalList.size())
//            numOfData = (long) hospitalList.size();
//        Log.d(TAG, "updateDatabaseHospital: " + numOfData);
//        for (int i = 0; i < numOfData; i++) {
//            Hospital hospital = hospitalList.get(i);
//            hospital.setName(hospital.getName() + " NEW");
//            if (saveHospital(hospital).equals(Observable.just(true)))
//                saveHospital(hospital);
//            else
//                return Observable.just(false);
//            hospitalList.set(i, hospital);
//        }
//        return saveHospitalList(hospitalList);
        return dbHelper.loadHospital(hospital).concatMap(hospital1 -> saveHospital(hospital));
    }

    @Override
    public Observable<Boolean> deleteDatabaseHospital(Hospital hospital) {
        return dbHelper.loadHospital(hospital).concatMap(hospital1 -> deleteHospital(hospital));
    }

//    @Override
//    public Observable<Boolean> seedDatabaseMedicine() {
//        Faker faker = new Faker();
//        return dbHelper.isMedicineEmpty()
//                .concatMap(aBoolean -> {
//                    if (aBoolean) {
//                        List<Medicine> medicineList = new ArrayList<>();
////                        for (int i = 0; i < 1082; i++) {
//                        for (int i = 0; i < 10; i++) {
//                            Medicine medicine = new Medicine();
//                            medicine.setName(faker.medical().medicineName());
//                            medicineList.add(medicine);
//                        }
////                        return saveMedicineList(medicineList);
//                    }
//                    return Observable.just(false);
//                });
//    }

    @Override
    public Observable<Boolean> seedDatabaseMedicine(Long numOfData) {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        Long numMedicine;
        String pathJson;
        if (numOfData < 100000) {
            numMedicine = (long) 10000;
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_10;
        } else if (numOfData < 500000) {
            numMedicine = (long) 100000;
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_100;
        } else if (numOfData < 1000000) {
            numMedicine = (long) 500000;
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_500;
        } else {
            numMedicine = (long) 1000000;
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_1000;
        }
        return dbHelper.getAllMedicine().count()
                .toObservable()
                .concatMap(aLong -> {
                    if (aLong < numMedicine) {
                        Type type = new TypeToken<List<Medicine>>(){}.getType();
                        List<Medicine> medicineList = gson.fromJson(
                                CommonUtils.loadJSONFromAsset(context,
                                        pathJson),
                                type);
                        return saveMedicineList(medicineList);
                    }
                    return Observable.just(false);
                });
    }

    @Override
    public Observable<Boolean> updateDatabaseMedicine(Medicine medicine) {
//        if (numOfData >= 10000) {
//            numOfData /= 1000;
//        }
//        if (numOfData >= 100) {
//            numOfData = (long) 100;
//        } else {
//            numOfData = (long) 10;
//        }
//        numOfData /= 10;
//        List<Medicine> medicineList = dbHelper.getAllMedicine().blockingSingle();
//        if (numOfData > medicineList.size())
//            numOfData = (long) medicineList.size();
//        Log.d(TAG, "updateDatabaseMedicine: " + numOfData);
//        for (int i = 0; i < numOfData; i++) {
//            Medicine medicine = medicineList.get(i);
//            medicine.setName(medicine.getName() + " NEW");
//            if (saveMedicine(medicine).equals(Observable.just(true)))
//                saveMedicine(medicine);
//            else
//                return Observable.just(false);
//            medicineList.set(i, medicine);
//        }
//        return saveMedicineList(medicineList);
        return dbHelper.loadMedicine(medicine).concatMap(medicine1 -> saveMedicine(medicine));
    }

    @Override
    public Observable<Boolean> deleteDatabaseMedicine(Medicine medicine) {
//        if (numOfData >= 10000) {
//            numOfData /= 1000;
//        }
//        if (numOfData >= 100) {
//            numOfData = (long) 100;
//        } else {
//            numOfData = (long) 10;
//        }
//        numOfData /= 10;
//        List<Medicine> medicineList = getAllMedicine().blockingSingle();
//        if (numOfData >= medicineList.size())
//            numOfData = (long) medicineList.size();
//        numOfData = medicineList.size() - numOfData;
//        Log.d(TAG, "deleteDatabaseMedicine: " + numOfData);
//        for (int i = 0; i < numOfData; i++) {
//            if (deleteMedicine(medicineList.get(i)).equals(Observable.just(true)))
//            Log.d(TAG, "deleteDatabaseMedicine: " + "MASUK");
//                deleteMedicine(medicineList.get(i));
//            else
//                return Observable.just(false);
//        }
//        saveMedicineList(getAllMedicine().blockingSingle());
        return dbHelper.loadMedicine(medicine).concatMap(medicine1 -> deleteMedicine(medicine));
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
    public Observable<Boolean> deleteHospital(Hospital hospital) {
        return dbHelper.deleteHospital(hospital);
    }

    @Override
    public Observable<Boolean> deleteMedicine(Medicine medicine) {
        return dbHelper.deleteMedicine(medicine);
    }

    @Override
    public Observable<Hospital> loadHospital(Hospital hospital) {
        return dbHelper.loadHospital(hospital);
    }

    @Override
    public Observable<Medicine> loadMedicine(Medicine medicine) {
        return dbHelper.loadMedicine(medicine);
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
    public Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return dbHelper.getMedicineForHospitalId(hospitalId);
    }

    @Override
    public Observable<Boolean> isHospitalEmpty() {
        return dbHelper.isHospitalEmpty();
    }

    @Override
    public Observable<Boolean> isMedicineEmpty() {
        return dbHelper.isMedicineEmpty();
    }

//    @Override
//    public Observable<Boolean> isDiseaseEmpty() {
//        return dbHelper.isDiseaseEmpty();
//    }
//
//    @Override
//    public Observable<Boolean> isSymptomEmpty() {
//        return dbHelper.isSymptomEmpty();
//    }

    @Override
    public Observable<Boolean> saveHospital(Hospital hospital) {
        return dbHelper.saveHospital(hospital);
    }

    @Override
    public Observable<Boolean> saveMedicine(Medicine medicine) {
        return dbHelper.saveMedicine(medicine);
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

}
