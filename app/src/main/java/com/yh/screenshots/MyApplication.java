package com.yh.screenshots;

import android.app.Application;
import android.content.Context;

/**
 * @author yh
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
