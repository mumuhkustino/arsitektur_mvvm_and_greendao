package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BR;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ActivityCrudBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;

import android.view.MenuItem;

import javax.inject.Inject;

public class CRUDActivity extends BaseActivity<ActivityCrudBinding, CRUDViewModel> {

    @Inject
    CRUDPagerAdapter pagerAdapter;

    private ActivityCrudBinding crudBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, CRUDActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crud;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(upIntent)
                        .startActivities();
            } else {
                NavUtils.navigateUpTo(this, upIntent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.crudBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void setUp() {
        setSupportActionBar(crudBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        pagerAdapter.setCount(4);

        crudBinding.crudViewPager.setAdapter(pagerAdapter);

        crudBinding.tabLayout.addTab(crudBinding.tabLayout.newTab().setText(R.string.tab_text_1));
        crudBinding.tabLayout.addTab(crudBinding.tabLayout.newTab().setText(R.string.tab_text_2));
        crudBinding.tabLayout.addTab(crudBinding.tabLayout.newTab().setText(R.string.tab_text_3));
        crudBinding.tabLayout.addTab(crudBinding.tabLayout.newTab().setText(R.string.tab_text_4));

        crudBinding.crudViewPager.setOffscreenPageLimit(crudBinding.tabLayout.getTabCount());
        crudBinding.crudViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(crudBinding.tabLayout));

        crudBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                crudBinding.crudViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}