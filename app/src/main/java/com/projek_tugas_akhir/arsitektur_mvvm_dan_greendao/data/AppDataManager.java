package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.DbHelper;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medicine;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.AppConstants;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context context;
    private final DbHelper dbHelper;

    @Inject
    public AppDataManager(Context context,
            DbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public Flowable<Boolean> seedDatabaseHospital(Long numOfData) {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        String pathJson;
        if (numOfData < 100000) {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_10;
        } else if (numOfData < 500000) {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_100;
        } else if (numOfData < 1000000) {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_500;
        } else {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_1000;
        }
        try {
                Type type = $Gson$Types
                        .newParameterizedTypeWithOwner(null, List.class,
                                Hospital.class);
                List<Hospital> hospitalList = gson.fromJson(
                        CommonUtils.loadJSONFromAsset(context,
                                pathJson),
                        type);
                return saveHospitalList(hospitalList);
            } catch (Exception e) {
            return Flowable.just(false);
        }
    }

    @Override
    public Flowable<Boolean> updateDatabaseHospital(Hospital hospital) {
        return dbHelper.loadHospital(hospital).concatMap(this::saveHospital);
    }

    @Override
    public Flowable<Boolean> deleteDatabaseHospital(Hospital hospital) {
        return dbHelper.loadHospital(hospital).concatMap(this::deleteHospital);
    }

    @Override
    public Flowable<Boolean> seedDatabaseMedicine(Long numOfData) {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        String pathJson;
        if (numOfData < 100000) {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_10;
        } else if (numOfData < 500000) {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_100;
        } else if (numOfData < 1000000) {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_500;
        } else {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_1000;
        }
        try {
                Type type = new TypeToken<List<Medicine>>(){}.getType();
                List<Medicine> medicineList = gson.fromJson(
                        CommonUtils.loadJSONFromAsset(context,
                                pathJson),
                        type);
                return saveMedicineList(medicineList);
        } catch (Exception e) {
            return Flowable.just(false);
        }
    }

    @Override
    public Flowable<Boolean> updateDatabaseMedicine(Medicine medicine) {
        return dbHelper.loadMedicine(medicine).concatMap(medicine1 -> saveMedicine(medicine));
    }

    @Override
    public Flowable<Boolean> deleteDatabaseMedicine(Medicine medicine) {
        return dbHelper.loadMedicine(medicine).concatMap(medicine1 -> deleteMedicine(medicine));
    }

    @Override
    public Flowable<Long> insertHospital(Hospital hospital) {
        return dbHelper.insertHospital(hospital);
    }

    @Override
    public Flowable<Long> insertMedicine(Medicine medicine) {
        return dbHelper.insertMedicine(medicine);
    }

    @Override
    public Flowable<Boolean> deleteHospital(Hospital hospital) {
        return dbHelper.deleteHospital(hospital);
    }

    @Override
    public Flowable<Boolean> deleteMedicine(Medicine medicine) {
        return dbHelper.deleteMedicine(medicine);
    }

    @Override
    public Flowable<Hospital> loadHospital(Hospital hospital) {
        return dbHelper.loadHospital(hospital);
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return dbHelper.loadMedicine(medicine);
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital() {
        return dbHelper.getAllHospital();
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
        return dbHelper.getAllMedicine();
    }

    @Override
    public Flowable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return dbHelper.getMedicineForHospitalId(hospitalId);
    }

    @Override
    public Flowable<Boolean> isHospitalEmpty() {
        return dbHelper.isHospitalEmpty();
    }

    @Override
    public Flowable<Boolean> isMedicineEmpty() {
        return dbHelper.isMedicineEmpty();
    }

    @Override
    public Flowable<Boolean> saveHospital(Hospital hospital) {
        return dbHelper.saveHospital(hospital);
    }

    @Override
    public Flowable<Boolean> saveMedicine(Medicine medicine) {
        return dbHelper.saveMedicine(medicine);
    }

    @Override
    public Flowable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
//        Log.d(TAG, "saveHospitalList: " + hospitalList.size());
        return dbHelper.saveHospitalList(hospitalList);
    }

    @Override
    public Flowable<Boolean> saveMedicineList(List<Medicine> medicineList) {
//        Log.d(TAG, "saveMedicineList: " + medicineList.size());
        return dbHelper.saveMedicineList(medicineList);
    }

}
