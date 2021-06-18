package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others;

import android.content.Context;
import android.content.SharedPreferences;

public class ExecutionTimePreference {
    private static final String PREFS_NAME = "user_pref";

    private static final String DATABASE_DELETE_TIME = "databaseDeleteTime";
    private static final String ALL_DELETE_TIME = "allDeleteTime";
    private static final String VIEW_DELETE_TIME = "viewDeleteTime";
    private static final String DATABASE_INSERT_TIME = "databaseInsertTime";
    private static final String ALL_INSERT_TIME = "allInsertTime";
    private static final String VIEW_INSERT_TIME = "viewInsertTime";
    private static final String DATABASE_SELECT_TIME = "databaseSelectTime";
    private static final String ALL_SELECT_TIME = "allSelectTime";
    private static final String VIEW_SELECT_TIME = "viewSelectTime";
    private static final String DATABASE_UPDATE_TIME = "databaseUpdateTime";
    private static final String ALL_UPDATE_TIME = "allUpdateTime";
    private static final String VIEW_UPDATE_TIME = "viewUpdateTime";

    private final SharedPreferences preferences;

    ExecutionTimePreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setExecutionTime(ExecutionTime executionTime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DATABASE_DELETE_TIME, executionTime.databaseDeleteTime);
        editor.putString(ALL_DELETE_TIME, executionTime.allDeleteTime);
        editor.putString(VIEW_DELETE_TIME, executionTime.viewDeleteTime);
        editor.putString(DATABASE_INSERT_TIME, executionTime.databaseInsertTime);
        editor.putString(ALL_INSERT_TIME, executionTime.allInsertTime);
        editor.putString(VIEW_INSERT_TIME, executionTime.viewInsertTime);
        editor.putString(DATABASE_SELECT_TIME, executionTime.databaseSelectTime);
        editor.putString(ALL_SELECT_TIME, executionTime.allSelectTime);
        editor.putString(VIEW_SELECT_TIME, executionTime.viewSelectTime);
        editor.putString(DATABASE_UPDATE_TIME, executionTime.databaseUpdateTime);
        editor.putString(ALL_UPDATE_TIME, executionTime.allUpdateTime);
        editor.putString(VIEW_UPDATE_TIME, executionTime.viewUpdateTime);
        editor.apply();
    }

    ExecutionTime getExecutionTime() {
        ExecutionTime executionTime = new ExecutionTime();
        executionTime.setDatabaseDeleteTime(preferences.getString(DATABASE_DELETE_TIME, ""));
        executionTime.setAllDeleteTime(preferences.getString(ALL_DELETE_TIME, ""));
        executionTime.setViewDeleteTime(preferences.getString(VIEW_DELETE_TIME, ""));
        executionTime.setDatabaseInsertTime(preferences.getString(DATABASE_INSERT_TIME, ""));
        executionTime.setAllInsertTime(preferences.getString(ALL_INSERT_TIME, ""));
        executionTime.setViewInsertTime(preferences.getString(VIEW_INSERT_TIME, ""));
        executionTime.setDatabaseSelectTime(preferences.getString(DATABASE_SELECT_TIME, ""));
        executionTime.setAllSelectTime(preferences.getString(ALL_SELECT_TIME, ""));
        executionTime.setViewSelectTime(preferences.getString(VIEW_SELECT_TIME, ""));
        executionTime.setDatabaseUpdateTime(preferences.getString(DATABASE_UPDATE_TIME, ""));
        executionTime.setAllUpdateTime(preferences.getString(ALL_UPDATE_TIME, ""));
        executionTime.setViewUpdateTime(preferences.getString(VIEW_UPDATE_TIME, ""));

        return executionTime;
    }

}
