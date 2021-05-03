package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db;


import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface DbHelper {

    Flowable<Long> insertHospital(Hospital hospital);

    Flowable<Long> insertMedicine( Medicine medicine);

    Flowable<Boolean> deleteHospital(Hospital hospital);

    Flowable<Boolean> deleteMedicine(Medicine medicine);

    Flowable<Hospital> loadHospital(Hospital hospital);

    Flowable<Medicine> loadMedicine(Medicine medicine);

    Flowable<List<Hospital>> getAllHospital();

    Flowable<List<Medicine>> getAllMedicine();

    Flowable<List<Medicine>> getMedicineForHospitalId(Long hospitalId);

    Flowable<Boolean> isHospitalEmpty();

    Flowable<Boolean> isMedicineEmpty();

    Flowable<Boolean> saveHospital(Hospital hospital);

    Flowable<Boolean> saveMedicine(Medicine medicine);

    Flowable<Boolean> saveHospitalList(List<Hospital> hospitalList);

    Flowable<Boolean> saveMedicineList(List<Medicine> medicineList);

}
