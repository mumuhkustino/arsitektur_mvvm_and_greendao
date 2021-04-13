package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DataManager extends DbHelper {

    Single<List<Medical>> getMedical(Long numOfData);

    Observable<Boolean> seedDatabaseHospital();

    Observable<Boolean> seedDatabaseMedicine();

    Observable<Boolean> seedDatabaseDisease();

    Observable<Boolean> seedDatabaseSymptom();

}
