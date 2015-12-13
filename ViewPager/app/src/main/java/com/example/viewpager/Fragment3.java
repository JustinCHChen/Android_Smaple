package com.example.viewpager;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewpager.BaseWidget.BaseFragment;

/**
 * Created by justin on 11/8/15.
 */
public class Fragment3 extends BaseFragment {
    private static final String TAG = "Fragment3";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, this.getClass().getSimpleName() + " onCreateView");
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onTabChangedResume() {
        Log.d(TAG, " onTabChangedResume ");
    }

    @Override
    public void onTabChangedPause() {
        Log.d(TAG, " onTabChangedPause ");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
