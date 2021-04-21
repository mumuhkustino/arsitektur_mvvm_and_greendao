package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DataManager extends DbHelper {

    Single<List<Medical>> getMedical();

    Single<List<Medical>> getMedical(Long numOfData);

    Observable<Boolean> seedDatabaseHospital();

    Observable<Boolean> seedDatabaseHospital(Long numOfData);

    Observable<Boolean> updateDatabaseHospital(Long numOfData);

    Observable<Boolean> deleteDatabaseHospital(Long numOfData);

    Observable<Boolean> seedDatabaseMedicine();

    Observable<Boolean> seedDatabaseMedicine(Long numOfData);

    Observable<Boolean> updateDatabaseMedicine(Long numOfData);

    Observable<Boolean> deleteDatabaseMedicine(Long numOfData);

    Observable<Boolean> seedDatabaseDisease();

    Observable<Boolean> seedDatabaseDisease(Long numOfData);

    Observable<Boolean> updateDatabaseDisease(Long numOfData);

    Observable<Boolean> deleteDatabaseDisease(Long numOfData);

    Observable<Boolean> seedDatabaseSymptom();

    Observable<Boolean> seedDatabaseSymptom(Long numOfData);

    Observable<Boolean> updateDatabaseSymptom(Long numOfData);

    Observable<Boolean> deleteDatabaseSymptom(Long numOfData);

}
