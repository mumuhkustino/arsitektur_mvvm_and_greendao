package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.BR;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ActivityCrudBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.di.component.ActivityComponent;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseActivity;

import javax.inject.Inject;

public class CRUDActivity extends BaseActivity<ActivityCrudBinding, CRUDViewModel> {

    @Inject
    CRUDPagerAdapter pagerAdapter;

    private ActivityCrudBinding crudBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crud;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable)
            ((Animatable) drawable).start();
        switch (item.getItemId()) {
            case R.id.action_export:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUp() {
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
                crudBinding.crudViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}