package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BR;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BuildConfig;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ActivityMainBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.NavHeaderMainBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    private ActivityMainBinding mActivityMainBinding;

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewmodel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentByTag();
//        if (fragment == null) {
            super.onBackPressed();
//        } else {
//            onFragmentDetached();
//        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        viewModel.setNavigator(this);

//        getActivityComponent().inject(this);

//        mActivityMainBinding.setViewmodel(mainViewModel);

        setUp();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawerLayout != null)
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    private void setUp() {
        mDrawerLayout = mActivityMainBinding.drawerMain;
        mToolbar = mActivityMainBinding.toolbar;
        mNavigationView = mActivityMainBinding.navigationView;

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        setupNavMenu();
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        viewModel.updateAppVersion(version);
//        viewModel.();
        setupContainerView();

    }

    private void setupContainerView() {
        new Handler(getMainLooper()).postDelayed(() -> {
           viewModel.loadHospital();
        }, 800);
    }

    private void setupNavMenu() {
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, mActivityMainBinding.navigationView, false);
        mActivityMainBinding.navigationView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setViewmodel(viewModel);
        View headerLayout = mNavigationView.getHeaderView(0);
        TextView nameTextView = headerLayout.findViewById(R.id.tvAppVersion);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_menu_insert:
                                return true;
                            default:
                                return false;
                        }
                    }
                }
        );
    }

}
