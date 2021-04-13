package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component;


import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module.FragmentModule;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.scope.FragmentScope;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    void inject(InsertFragment fragment);

}
