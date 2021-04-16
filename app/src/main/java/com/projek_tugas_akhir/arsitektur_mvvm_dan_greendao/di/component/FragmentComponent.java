package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component;


import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module.FragmentModule;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.scope.FragmentScope;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete.DeleteFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select.SelectFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update.UpdateFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    void inject(InsertFragment fragment);

    void inject(SelectFragment fragment);

    void inject(UpdateFragment fragment);

    void inject(DeleteFragment fragment);

}
