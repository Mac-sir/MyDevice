package com.example.mydevice;

import android.app.Application;
import android.content.Context;

import com.github.gzuliyujiang.oaid.DeviceIdentifier;

public class MyApp extends Application {
    private boolean privacyPolicyAgreed = false;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       // MultiDex.install(this);
        privacyPolicyAgreed = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注意APP合规性，若最终用户未同意隐私政策则不要调用
        if (privacyPolicyAgreed) {
            DeviceIdentifier.register(this);
        }
    }
}
