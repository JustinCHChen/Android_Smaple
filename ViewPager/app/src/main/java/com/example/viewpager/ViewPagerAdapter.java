package com.example.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by justin on 11/8/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private int mTabCount;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment1 tab1 = new Fragment1();
                return tab1;
            case 1:
                Fragment2 tab2 = new Fragment2();
                return tab2;
            case 2:
                Fragment3 tab3 = new Fragment3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragmentObject = super.instantiateItem(container, position);
        while (mFragments.size() <= position) {
            mFragments.add(null);
        }
        mFragments.set(position, (Fragment) fragmentObject);
        return fragmentObject;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        while (mFragments.size() <= position) {
            mFragments.add(null);
        }
        mFragments.set(position, null);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public Fragment getExistFragment(int position) {
        Fragment fragment = null;
        if (mFragments.size() > position) {
            fragment = mFragments.get(position);
        }
        return fragment;
    }
}
