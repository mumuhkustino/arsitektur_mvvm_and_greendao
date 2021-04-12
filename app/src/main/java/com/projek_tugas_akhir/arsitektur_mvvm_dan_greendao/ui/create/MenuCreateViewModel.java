package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.create;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

public class MenuCreateViewModel extends BaseViewModel<MenuCreateNavigator> {

    public MenuCreateViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

//    long executionTime = 0;

//    void calculateTime(long beforeTime, long afterTime) {
//        executionTime = beforeTime - afterTime;
//    }

}
