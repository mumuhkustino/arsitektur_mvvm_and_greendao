package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;

import io.reactivex.Flowable;

public interface DataManager extends DbHelper {

//    Single<List<Medical>> getMedical();

//    Single<List<Medical>> getMedical(Long numOfData);

//    Observable<Boolean> seedDatabaseHospital();

    Flowable<Boolean> seedDatabaseHospital(Long numOfData);

    Flowable<Boolean> updateDatabaseHospital(Hospital hospital);

    Flowable<Boolean> deleteDatabaseHospital(Hospital hospital);

//    Observable<Boolean> seedDatabaseMedicine();

    Flowable<Boolean> seedDatabaseMedicine(Long numOfData);

    Flowable<Boolean> updateDatabaseMedicine(Medicine medicine);

    Flowable<Boolean> deleteDatabaseMedicine(Medicine medicine);

}
