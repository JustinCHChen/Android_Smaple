package com.example.justin_chen.drawer;

import android.app.Application;

/**
 * Created by justin_chen on 2016/2/4.
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }
}
