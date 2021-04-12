package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.DaoMaster;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.DatabaseInfo;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:

        }
    }
}
