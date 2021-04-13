package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model;

public class Medical {

//    private Long id;

    private String hospitalName;

    private String medicineName;

    private String diseaseName;

    private String symptomName;

    public Medical(
//            Long id,
            String hospitalName, String medicineName, String diseaseName, String symptomName) {
//        this.id = id;
        this.hospitalName = hospitalName;
        this.medicineName = medicineName;
        this.diseaseName = diseaseName;
        this.symptomName = symptomName;
    }

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
}
