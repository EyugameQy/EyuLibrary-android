package com.eyu.opensdk.integration.quick_start;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.eyu.opensdk.ad.AdConfig;
import com.eyu.opensdk.ad.AdPlatform;
import com.eyu.opensdk.ad.EyuAdManager;
import com.eyu.opensdk.ad.EyuAdsListener;
import com.eyu.opensdk.ad.base.model.AdFormat;
import com.eyu.opensdk.ad.base.model.PlatformExtras;
import com.eyu.opensdk.core.InitializerBuilderImpl;
import com.eyu.opensdk.core.SdkCompat;

import java.lang.ref.WeakReference;

class QuickStarter {

    private static QuickStarter instance;

    private WeakReference<Activity> mActivityReference = new WeakReference<>(null);

    private QuickStarter() {
    }

    public static synchronized QuickStarter getInstance() {
        if (instance == null) {
            instance = new QuickStarter();
        }
        return instance;
    }

    public void setActivity(Activity activity) {
        mActivityReference = new WeakReference<>(activity);
    }


    public Activity getActivity() {
        return mActivityReference.get();
    }

    protected void initSdkCore(QuickApplication application) {
        InitializerBuilderImpl builder = new InitializerBuilderImpl();
        builder.setDebugMode(BuildConfig.DEBUG);
        //appsflyer配置
        String key = Utils.getStringResourceText(application, "appsflyer_key");
        if (!TextUtils.isEmpty(key)) {
            builder.configAppsFlyer(key);
        }

        //数数的统计初始化
        key = Utils.getStringResourceText(application, "shushu_key");
        if (!TextUtils.isEmpty(key)) {
            builder.configThinkData(key);
        }

        //热云初始化
        key = Utils.getStringResourceText(application, "reyun_key");
        if (!TextUtils.isEmpty(key)) {
            //国内会用到
            builder.configTrackingIO(key);
        }

        SdkCompat.getInstance().init(application, builder);
    }

    protected void initAd(Activity activity) {
        AdConfig adConfig = new AdConfig();
        adConfig.setDebugMode(BuildConfig.DEBUG);
        adConfig.setAdPlaceConfigResource(activity, R.raw.and_ad_setting);
        adConfig.setAdKeyConfigResource(activity, R.raw.and_ad_key_setting);
        adConfig.setAdGroupConfigResource(activity, R.raw.and_ad_cache_setting);

        Bundle bundle = new Bundle();
        String key = Utils.getStringResourceText(activity, "pangle_key");
        String appName = Utils.getStringResourceText(activity, "pangle_app_name");

        if (!TextUtils.isEmpty(key)) {
            bundle.putString(PlatformExtras.COMMON_APP_ID, key);
            bundle.putString("app_name", appName);
            adConfig.addPlatformConfig(AdPlatform.PANGLE, bundle);
        }

        EyuAdManager.getInstance().config(activity, adConfig, new EyuAdsListener() {

            @Override
            public void onAdReward(AdFormat adFormat, String s) {
                //激励视频获得奖励
            }

            @Override
            public void onAdLoaded(AdFormat adFormat, String s) {
                //广告加载成功
            }

            @Override
            public void onAdShowed(AdFormat adFormat, String s) {
                //广告展示
            }

            @Override
            public void onAdClosed(AdFormat adFormat, String s) {
                //广告关闭
            }

            @Override
            public void onAdClicked(AdFormat adFormat, String s) {
                //点击广告
            }

            @Override
            public void onDefaultNativeAdClicked() {

            }

            @Override
            public void onAdLoadFailed(AdFormat adFormat, String s, String s1, int i) {
                //广告加载失败
            }

            @Override
            public void onImpression(AdFormat adFormat, String s) {
                //广告展示
            }
        });

        boolean is_overseas = Utils.getBooleanResourceValue(activity, "is_overseas");
        if (!is_overseas) {
            SdkCompat.getInstance().requestPermissions(activity);
        }
    }
}
