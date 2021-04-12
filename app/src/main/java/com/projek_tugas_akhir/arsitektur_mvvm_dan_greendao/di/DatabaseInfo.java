package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {

}
