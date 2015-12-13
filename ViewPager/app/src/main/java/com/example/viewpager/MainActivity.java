package com.example.viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.viewpager.BaseWidget.BaseFragment;
import com.example.viewpager.BaseWidget.FragmentLifeCycle;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private static final String TAG = "MainActivity";
    static final int DEFAULT_POSITION = 1;

    private View mTabView1;
    private View mTabView2;
    private View mTabView3;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;
    private int mPreviousPosition = DEFAULT_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        initTabLayout();
        initViewPager();


    }

    private void initTabLayout() {
        mTabView1 = getLayoutInflater().inflate(R.layout.tab_customize, null);
        mTabView2 = getLayoutInflater().inflate(R.layout.tab_customize, null);
        mTabView3 = getLayoutInflater().inflate(R.layout.tab_customize, null);

        TextView tabTitle1 = (TextView) mTabView1.findViewById(R.id.tab_title);
        TextView tabTitle2 = (TextView) mTabView2.findViewById(R.id.tab_title);
        TextView tabTitle3 = (TextView) mTabView3.findViewById(R.id.tab_title);

        tabTitle1.setText("1");
        tabTitle2.setText("2");
        tabTitle3.setText("3");

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(mTabView1));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(mTabView2));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(mTabView3));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mTabLayout.setOnTabSelectedListener(this);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setCurrentItem(DEFAULT_POSITION);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
        BaseFragment previousFragment = (BaseFragment) mViewPagerAdapter.getExistFragment(mPreviousPosition);
        if (previousFragment != null) {
            previousFragment.performTabPause();
        }

        BaseFragment newestFragment = (BaseFragment) mViewPagerAdapter.getExistFragment(tab.getPosition());
        if (newestFragment != null) {
            newestFragment.performTabResume();
        }
        mPreviousPosition = tab.getPosition();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
