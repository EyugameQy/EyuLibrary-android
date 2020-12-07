package com.eyu.test;

import android.app.Application;

import com.eyu.opensdk.core.InitializerBuilderImpl;
import com.eyu.opensdk.core.SdkCompat;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final InitializerBuilderImpl builder = new InitializerBuilderImpl();
        //appsflyer
        //builder.initAppsFlyer("key");
        //热云
        //builder.initTracking(this,"appKey","channle");
        //友盟
        //builder.initUmeng("appKey","channle");

        //builder.initThinkData("key","serverUrl");
        SdkCompat.getInstance().init(this, builder);
    }
}
