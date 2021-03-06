package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;

// Model dari Object Hospital
@Entity(nameInDb = "hospitals")
public class Hospital {

    // Attribute id berupa integer (long)
    @Expose
    @SerializedName("id") // Pemrosesan konversi file json ke dalam objek
    @Id // Annotation untuk menyatakan attribut ini sebagai id
    private Long id; // atau primary key

    @Expose
    @SerializedName("hospitalName")
    @Property(nameInDb = "name") // Annotation untuk menyatakan
    private String name; // attribut ini merupakan property

    @ToMany(referencedJoinProperty = "hospitalId")  //Annotation untuk menyatakan satu object hospital
    private List<Medicine> medicineList;            //memiliki banyak medicine dengan foreign key hospitalId

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;    //Hasil generate otomatis dari library greendao untuk annotation ToMany

    /** Used for active entity operations. */
    @Generated(hash = 679648518)
    private transient HospitalDao myDao;        //Hasil generate otomatis dari library greendao untuk annotation ToMany

    @Generated(hash = 714822730)
    public Hospital(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1301721268)
    public Hospital() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2131197227)
    public List<Medicine> getMedicineList() {
        if (medicineList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MedicineDao targetDao = daoSession.getMedicineDao();
            List<Medicine> medicineListNew = targetDao._queryHospital_MedicineList(id);
            synchronized (this) {
                if (medicineList == null) {
                    medicineList = medicineListNew;
                }
            }
        }
        return medicineList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1205167936)
    public synchronized void resetMedicineList() {
        medicineList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 267021903)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHospitalDao() : null;
    }

}
