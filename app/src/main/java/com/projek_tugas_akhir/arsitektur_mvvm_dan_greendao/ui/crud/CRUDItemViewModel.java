package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import androidx.databinding.ObservableField;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;

public class CRUDItemViewModel {

    public final ObservableField<Long> id;

    public final ObservableField<String> hospitalName;

    public final ObservableField<String> medicineName;

//    public final ObservableField<String> diseaseName;

//    public final ObservableField<String> symptomName;

    public final Medical medical;

    public CRUDItemViewModel(Long id, Medical medical) {
        this.medical = medical;
        this.id = new ObservableField<>(id);
        this.hospitalName = new ObservableField<>(medical.getHospitalName());
        this.medicineName = new ObservableField<>(medical.getMedicineName());
//        this.diseaseName = new ObservableField<>(medical.getDiseaseName());
//        this.symptomName = new ObservableField<>(medical.getSymptomName());
    }

}
