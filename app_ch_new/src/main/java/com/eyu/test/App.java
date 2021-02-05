package com.eyu.test;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.eyu.opensdk.core.InitializerBuilderImpl;
import com.eyu.opensdk.core.SdkCompat;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final InitializerBuilderImpl builder = new InitializerBuilderImpl();
        //appsflyer
        //builder.configAppsFlyer("key");
        //热云
        builder.configThinkData("appKey");
        //友盟
//        builder.configUmeng("appKey","channle");

//        builder.configThinkData("key");
        SdkCompat.getInstance().init(this, builder);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
