package com.eyu.app_overseas_new;

import android.app.Application;

import com.eyu.opensdk.core.InitializerBuilderImpl;
import com.eyu.opensdk.core.SdkCompat;

import java.util.HashMap;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final InitializerBuilderImpl builder = new InitializerBuilderImpl();
        //builder.initAppsFlyer("");
        builder.initRemoteConfig(new HashMap<String, Object>());
        //builder.initThinkData("","");
        SdkCompat.getInstance().init(this, builder);
    }
}
