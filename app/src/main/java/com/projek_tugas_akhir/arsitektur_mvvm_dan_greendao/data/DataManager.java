package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DataManager extends DbHelper {

//    Single<List<Medical>> getMedical();

//    Single<List<Medical>> getMedical(Long numOfData);

    Observable<Boolean> seedDatabaseHospital();

    Observable<Boolean> seedDatabaseHospital(Long numOfData);

    Observable<Boolean> updateDatabaseHospital(Long numOfData);

    Observable<Boolean> deleteDatabaseHospital(Long numOfData);

    Observable<Boolean> seedDatabaseMedicine();

    Observable<Boolean> seedDatabaseMedicine(Long numOfData);

    Observable<Boolean> updateDatabaseMedicine(Medicine medicine);

    Observable<Boolean> deleteDatabaseMedicine(Long numOfData);

}
