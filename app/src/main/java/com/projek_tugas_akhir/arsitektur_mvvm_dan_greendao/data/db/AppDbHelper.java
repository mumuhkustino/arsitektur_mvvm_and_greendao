package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoMaster;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoSession;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.HospitalDao;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.MedicineDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession daoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Flowable<Long> insertHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                return daoSession.getHospitalDao().insertOrReplace(hospital);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Long> insertMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                return daoSession.getMedicineDao().insertOrReplace(medicine);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                final Hospital unique = daoSession.getHospitalDao().queryBuilder()
                        .where(HospitalDao.Properties.Id.eq(hospital.getId())).unique();
                daoSession.getHospitalDao().deleteInTx(unique);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                final Medicine unique = daoSession.getMedicineDao().queryBuilder()
                        .where(MedicineDao.Properties.Id.eq(medicine.getId())).unique();
                daoSession.getMedicineDao().deleteInTx(unique);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Hospital> loadHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                return daoSession.getHospitalDao().queryBuilder()
                        .where(HospitalDao.Properties.Id.eq(hospital.getId())).unique();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                return daoSession.getMedicineDao().queryBuilder()
                        .where(MedicineDao.Properties.Id.eq(medicine.getId())).unique();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital() {
//        return Flowable.fromCallable(() -> daoSession.getHospitalDao().queryBuilder().list());
        return Flowable.fromCallable(() -> daoSession.getHospitalDao().loadAll());
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
//        return Flowable.fromCallable(() -> daoSession.getMedicineDao().queryBuilder().list());
        return Flowable.fromCallable(() -> daoSession.getMedicineDao().loadAll());
    }

    @Override
    public Flowable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return Flowable.fromCallable(() -> daoSession.getMedicineDao()._queryHospital_MedicineList(hospitalId));
    }

    @Override
    public Flowable<Boolean> isHospitalEmpty() {
        return Flowable.fromCallable(() -> daoSession.getHospitalDao().loadAll().isEmpty());
    }

    @Override
    public Flowable<Boolean> isMedicineEmpty() {
        return Flowable.fromCallable(() -> daoSession.getMedicineDao().loadAll().isEmpty());
    }

    @Override
    public Flowable<Boolean> saveHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                daoSession.getHospitalDao().insertOrReplaceInTx(hospital);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                daoSession.getMedicineDao().insertOrReplaceInTx(medicine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
        return Flowable.fromCallable(() -> {
            daoSession.getHospitalDao().insertOrReplaceInTx(hospitalList);
            return true;
        });
    }

    @Override
    public Flowable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        return Flowable.fromCallable(() -> {
            daoSession.getMedicineDao().insertOrReplaceInTx(medicineList);
            return true;
        });
    }

}
