package com.example.bottombar;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;

/**
 * Time:13:36
 * Author:lenovo
 * Description:新生成的类
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(getApplicationContext());
    }
}
