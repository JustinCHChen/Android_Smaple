package com.example.viewpager.BaseWidget;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by justin on 11/16/15.
 */
public abstract class BaseFragment extends Fragment implements FragmentLifeCycle {
    final String TAG = this.getClass().getSimpleName();
    private boolean mIsOnTabChangedResume = false;

    @Override
    public void onResume() {
        Log.d(TAG, "onResume ");
        super.onResume();
        if (getUserVisibleHint()) {
            performTabResume();
        }
    }

    public void performTabResume() {
        if (!mIsOnTabChangedResume) {
            mIsOnTabChangedResume = true;
            onTabChangedResume();
        }
    }

    public void performTabPause() {
        if (mIsOnTabChangedResume) {
            mIsOnTabChangedResume = false;
            onTabChangedPause();
        }


    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause ");
        super.onPause();
        if (getUserVisibleHint()) {
            performTabPause();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
        super.onDestroy();
    }


}
