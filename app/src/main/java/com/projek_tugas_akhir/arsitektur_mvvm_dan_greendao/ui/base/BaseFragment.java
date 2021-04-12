package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.MVVMgreenDao;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.DaggerFragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.FragmentComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.module.FragmentModule;

import javax.inject.Inject;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    private BaseActivity baseActivity;
    private View view;
    private T viewDataBinding;

    @Inject
    protected V viewModel;

    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.baseActivity = baseActivity;
            baseActivity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection(getBuildComponent());
        super.onCreate(savedInstanceState);
    }

    private FragmentComponent getBuildComponent() {
//        return null;
        return DaggerFragmentComponent.builder()
                .applicationComponent(((MVVMgreenDao)(getContext().getApplicationContext())).applicationComponent)
                .fragmentModule(new FragmentModule(this))
                .build();
    }


    public abstract void performDependencyInjection(FragmentComponent buildComponent);

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
