package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.List;

public class SelectViewModel extends BaseViewModel<SelectNavigator> {

//    private final MutableLiveData<List<Medical>> medicalListLiveData;

    public SelectViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }
}
