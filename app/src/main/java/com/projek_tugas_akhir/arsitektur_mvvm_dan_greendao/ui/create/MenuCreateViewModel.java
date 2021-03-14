package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.create;

import androidx.lifecycle.ViewModel;

public class MenuCreateViewModel extends ViewModel {

    long executionTime = 0;

    void calculateTime(long beforeTime, long afterTime) {
        executionTime = beforeTime - afterTime;
    }

}
