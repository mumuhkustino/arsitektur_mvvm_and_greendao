package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "medicines")
public class Medicine {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "hospitalId")
    private Long hospitalId;

    @Property(nameInDb = "name")
    private String name;

    @Generated(hash = 1489536452)
    public Medicine(Long id, Long hospitalId, String name) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.name = name;
    }

    @Generated(hash = 1065091254)
    public Medicine() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
