package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update;

import androidx.databinding.ObservableField;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;

public class UpdateItemViewModel {

    public final ObservableField<Long> id;

    public final ObservableField<String> hospitalName;

    public final ObservableField<String> medicineName;

    public final Medical medical;

    public UpdateItemViewModel(Long id, Medical medical) {
        this.medical = medical;
        this.id = new ObservableField<>(id);
        this.hospitalName = new ObservableField<>(medical.getHospitalName());
        this.medicineName = new ObservableField<>(medical.getMedicineName());
    }

}
