package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db;


import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Disease;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Symptom;

import java.util.List;
import io.reactivex.Observable;

public interface DbHelper {

    Observable<Long> insertHospital(final Hospital hospital);

    Observable<Long> insertMedicine(final Medicine medicine);

    Observable<Long> insertDisease(final Disease disease);

    Observable<Long> insertSymptom(final Symptom symptom);

    Observable<Boolean> deleteHospital(final Hospital hospital);

    Observable<Boolean> deleteMedicine(final Medicine medicine);

    Observable<Boolean> deleteDisease(final Disease disease);

    Observable<Boolean> deleteSymptom(final Symptom symptom);

    Observable<List<Hospital>> getAllHospital();

    Observable<List<Medicine>> getAllMedicine();

    Observable<List<Disease>> getAllDisease();

    Observable<List<Symptom>> getAllSymptom();

    Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId);

    Observable<List<Disease>> getDiseaseForHospitalId(Long hospitalId);

    Observable<List<Symptom>> getSymptomForDiseaseId(Long diseaseId);

    Observable<Boolean> isHospitalEmpty();

    Observable<Boolean> isMedicineEmpty();

    Observable<Boolean> isDiseaseEmpty();

    Observable<Boolean> isSymptomEmpty();

    Observable<Boolean> saveHospital(Hospital hospital);

    Observable<Boolean> saveMedicine(Medicine medicine);

    Observable<Boolean> saveDisease(Disease disease);

    Observable<Boolean> saveSymptom(Symptom symptom);

    Observable<Boolean> saveHospitalList(List<Hospital> hospitalList);

    Observable<Boolean> saveMedicineList(List<Medicine> medicineList);

    Observable<Boolean> saveDiseaseList(List<Disease> diseaseList);

    Observable<Boolean> saveSymptomList(List<Symptom> symptomList);

}
