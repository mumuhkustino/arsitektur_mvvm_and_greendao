package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;

import java.util.List;

public interface InsertNavigator {

    void handleError(Throwable throwable);

    void updateMedical(List<Medical> medicalList);

}
