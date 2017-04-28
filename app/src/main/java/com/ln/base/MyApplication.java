package com.ln.base;

import android.app.Application;
import android.content.Context;

/**
 * description:
 * Created by liNan on 2017/4/26 14:45
 */

public class MyApplication extends Application {
    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
}
