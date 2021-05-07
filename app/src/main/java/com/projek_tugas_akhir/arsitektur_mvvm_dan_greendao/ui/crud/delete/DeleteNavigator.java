package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;

import java.util.List;

public interface DeleteNavigator {

    void handleError(Throwable throwable);

    void onClick();

    void updateMedical(List<Medical> medicalList);

}
