package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoMaster;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoSession;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Disease;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Symptom;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession daoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }


    @Override
    public Observable<Long> insertHospital(Hospital hospital) {
        return Observable.fromCallable(() -> daoSession.getHospitalDao().insert(hospital));
    }

    @Override
    public Observable<Long> insertMedicine(Medicine medicine) {
        return Observable.fromCallable(() -> daoSession.getMedicineDao().insert(medicine));
    }

    @Override
    public Observable<Long> insertDisease(Disease disease) {
        return Observable.fromCallable(() -> daoSession.getDiseaseDao().insert(disease));
    }

    @Override
    public Observable<Long> insertSymptom(Symptom symptom) {
        return Observable.fromCallable(() -> daoSession.getSymptomDao().insert(symptom));
    }

    @Override
    public Observable<List<Hospital>> getAllHospital() {
        return Observable.fromCallable(() -> daoSession.getHospitalDao().loadAll());
    }

    @Override
    public Observable<List<Medicine>> getAllMedicine() {
        return Observable.fromCallable(() -> daoSession.getMedicineDao().loadAll());
    }

    @Override
    public Observable<List<Disease>> getAllDisease() {
        return Observable.fromCallable(() -> daoSession.getDiseaseDao().loadAll());
    }

    @Override
    public Observable<List<Symptom>> getAllSymptom() {
        return Observable.fromCallable(() -> daoSession.getSymptomDao().loadAll());
    }

    @Override
    public Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return Observable.fromCallable(() -> daoSession.getMedicineDao()._queryHospital_MedicineList(hospitalId));
    }

    @Override
    public Observable<List<Disease>> getDiseaseForHospitalId(Long hospitalId) {
        return Observable.fromCallable(() -> daoSession.getDiseaseDao()._queryHospital_DiseaseList(hospitalId));
    }

    @Override
    public Observable<List<Symptom>> getSymptomForDiseaseId(Long diseaseId) {
        return Observable.fromCallable(() -> daoSession.getSymptomDao()._queryDisease_SymptomList(diseaseId));
    }

    @Override
    public Observable<Boolean> isHospitalEmpty() {
        return Observable.fromCallable(() -> daoSession.getHospitalDao().loadAll().isEmpty());
    }

    @Override
    public Observable<Boolean> isMedicineEmpty() {
        return Observable.fromCallable(() -> daoSession.getMedicineDao().loadAll().isEmpty());
    }

    @Override
    public Observable<Boolean> isDiseaseEmpty() {
        return Observable.fromCallable(() -> daoSession.getDiseaseDao().loadAll().isEmpty());
    }

    @Override
    public Observable<Boolean> isSymptomEmpty() {
        return Observable.fromCallable(() -> daoSession.getSymptomDao().loadAll().isEmpty());
    }

    @Override
    public Observable<Boolean> saveHospital(Hospital hospital) {
        return Observable.fromCallable(() -> {
            daoSession.getHospitalDao().insertInTx(hospital);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveMedicine(Medicine medicine) {
        return Observable.fromCallable(() -> {
            daoSession.getMedicineDao().insertInTx(medicine);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveDisease(Disease disease) {
        return Observable.fromCallable(() -> {
            daoSession.getDiseaseDao().insertInTx(disease);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveSymptom(Symptom symptom) {
        return Observable.fromCallable(() -> {
            daoSession.getSymptomDao().insertInTx(symptom);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
        return Observable.fromCallable(() -> {
            daoSession.getHospitalDao().insertInTx(hospitalList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        return Observable.fromCallable(() -> {
            daoSession.getMedicineDao().insertInTx(medicineList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveDiseaseList(List<Disease> diseaseList) {
        return Observable.fromCallable(() -> {
            daoSession.getDiseaseDao().insertInTx(diseaseList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveSymptomList(List<Symptom> symptomList) {
        return Observable.fromCallable(() -> {
            daoSession.getSymptomDao().insertInTx(symptomList);
            return true;
        });
    }

}
