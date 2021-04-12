package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;

import io.reactivex.Observable;

public interface DataManager extends DbHelper {

    Observable<Boolean> seedDatabaseHospital();

    Observable<Boolean> seedDatabaseMedicine();

    Observable<Boolean> seedDatabaseDisease();

    Observable<Boolean> seedDatabaseSymptom();

}
