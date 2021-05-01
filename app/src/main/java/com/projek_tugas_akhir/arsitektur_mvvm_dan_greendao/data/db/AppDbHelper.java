package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db;

import android.database.sqlite.SQLiteDatabase;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoMaster;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoSession;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.HospitalDao;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.MedicineDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession daoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertHospital(Hospital hospital) {
        return Observable.fromCallable(() -> {
            try {
                return daoSession.getHospitalDao().insertOrReplace(hospital);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Observable<Long> insertMedicine(Medicine medicine) {
        return Observable.fromCallable(() -> {
            try {
                return daoSession.getMedicineDao().insertOrReplace(medicine);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteHospital(Hospital hospital) {
        return Observable.fromCallable(() -> {
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
    public Observable<Boolean> deleteMedicine(Medicine medicine) {
        return Observable.fromCallable(() -> {
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
    public Observable<Hospital> loadHospital(Hospital hospital) {
        return Observable.fromCallable(() -> {
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
    public Observable<Medicine> loadMedicine(Medicine medicine) {
        return Observable.fromCallable(() -> {
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
    public Observable<List<Hospital>> getAllHospital() {
        return Observable.fromCallable(() -> daoSession.getHospitalDao().queryBuilder().list());
    }

    @Override
    public Observable<List<Medicine>> getAllMedicine() {
        return Observable.fromCallable(() -> daoSession.getMedicineDao().queryBuilder().list());
    }

    @Override
    public Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return Observable.fromCallable(() -> daoSession.getMedicineDao()._queryHospital_MedicineList(hospitalId));
    }

    @Override
    public Observable<Boolean> isHospitalEmpty() {
        return Observable.fromCallable(() -> daoSession.getHospitalDao().loadAll().isEmpty());
    }

    @Override
    public Observable<Boolean> isMedicineEmpty() {
        return Observable.fromCallable(() -> daoSession.getMedicineDao().loadAll().isEmpty());
    }

    @Override
    public Observable<Boolean> saveHospital(Hospital hospital) {
        return Observable.fromCallable(() -> {
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
    public Observable<Boolean> saveMedicine(Medicine medicine) {
        return Observable.fromCallable(() -> {
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
    public Observable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
        return Observable.fromCallable(() -> {
            daoSession.getHospitalDao().insertOrReplaceInTx(hospitalList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        return Observable.fromCallable(() -> {
            daoSession.getMedicineDao().insertOrReplaceInTx(medicineList);
            return true;
        });
    }

}
