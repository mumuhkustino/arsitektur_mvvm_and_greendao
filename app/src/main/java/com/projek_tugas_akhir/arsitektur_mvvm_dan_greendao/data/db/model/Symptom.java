package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "symptoms")
public class Symptom {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "diseaseId")
    private Long diseaseId;

    @Property(nameInDb = "name")
    private String name;

    @Generated(hash = 1043661067)
    public Symptom(Long id, Long diseaseId, String name) {
        this.id = id;
        this.diseaseId = diseaseId;
        this.name = name;
    }

    @Generated(hash = 2107028981)
    public Symptom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
