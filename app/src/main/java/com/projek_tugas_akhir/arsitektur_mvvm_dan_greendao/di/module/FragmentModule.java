package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ViewModelProviderFactory;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.DataManager;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete.DeleteAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete.DeleteViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select.SelectAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select.SelectViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update.UpdateAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update.UpdateViewModel;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.function.Supplier;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    @Named("insert")
    InsertAdapter provideInsertAdapter() {
        return new InsertAdapter(new ArrayList<>());
    }

    @Provides
    @Named("select")
    SelectAdapter provideSelectAdapter() {
        return new SelectAdapter(new ArrayList<>());
    }

    @Provides
    @Named("update")
    UpdateAdapter provideUpdateAdapter() {
        return new UpdateAdapter(new ArrayList<>());
    }

    @Provides
    @Named("delete")
    DeleteAdapter provideDeleteAdapter() {
        return new DeleteAdapter(new ArrayList<>());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    DeleteViewModel provideDeleteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<DeleteViewModel> supplier = () -> new DeleteViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<DeleteViewModel> factory = new ViewModelProviderFactory<>(DeleteViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(DeleteViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    InsertViewModel provideInsertViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<InsertViewModel> supplier = () -> new InsertViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<InsertViewModel> factory = new ViewModelProviderFactory<>(InsertViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(InsertViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    SelectViewModel provideSelectViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SelectViewModel> supplier = () -> new SelectViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SelectViewModel> factory = new ViewModelProviderFactory<>(SelectViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(SelectViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    UpdateViewModel provideUpdateViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<UpdateViewModel> supplier = () -> new UpdateViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<UpdateViewModel> factory = new ViewModelProviderFactory<>(UpdateViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(UpdateViewModel.class);
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Provides
//    CRUDViewModel provideCRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
//        Supplier<CRUDViewModel> supplier = () -> new CRUDViewModel(dataManager, schedulerProvider);
//        ViewModelProviderFactory<CRUDViewModel> factory = new ViewModelProviderFactory<>(CRUDViewModel.class, supplier);
//        return new ViewModelProvider(fragment, factory).get(CRUDViewModel.class);
//    }

}
