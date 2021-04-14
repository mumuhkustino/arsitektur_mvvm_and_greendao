package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;

import java.util.List;

public interface CRUDNavigator {

    void handleError(Throwable throwable);

    void onClick();

    void updateMedical(List<Medical> medicalList);

}
