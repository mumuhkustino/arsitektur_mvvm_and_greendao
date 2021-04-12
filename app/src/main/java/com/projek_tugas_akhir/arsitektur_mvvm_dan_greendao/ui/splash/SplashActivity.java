package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.splash;

import android.content.Intent;

import androidx.annotation.Nullable;
import android.os.Bundle;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BR;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ActivitySplashBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseActivity;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {
    @Override
    public int getBindingVariable() {
        return BR.viewmodel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        viewModel.startSeeding();
    }

}
