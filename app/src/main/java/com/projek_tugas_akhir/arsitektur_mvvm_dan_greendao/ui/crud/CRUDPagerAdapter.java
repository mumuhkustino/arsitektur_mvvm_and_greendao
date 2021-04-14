package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertFragment;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select.SelectFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CRUDPagerAdapter extends FragmentPagerAdapter {

//    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
//    private final Context mContext;
    private int tabCount;

    public CRUDPagerAdapter(FragmentManager fm) {
        super(fm);
        this.tabCount = 0;
//        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return InsertFragment.newInstance();
            case 1:
                return SelectFragment.newInstance();
            case 2:
                return InsertFragment.newInstance();
            case 3:
                return InsertFragment.newInstance();
            default:
                return null;
        }
    }

    public void setCount(int tabCount) {
        this.tabCount = tabCount;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[position]);
//    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return tabCount;
    }
}