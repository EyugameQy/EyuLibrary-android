package com.eyu.test;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.appsflyer.AppsFlyerConversionListener;
import com.eyu.common.SdkHelper;
import com.eyu.common.ad.EyuAdManager;
import com.eyu.common.ad.EyuAdsListener;
import com.eyu.common.ad.model.AdConfig;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements EyuAdsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SdkHelper.init(this);
        String[] permissions = {Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, SdkHelper.PERMISSIONS_REQ_CODE);
        SdkHelper.initTracking(this, "yourAppKey");
        SdkHelper.initUmSdk(this, "appKey", "channel");
        SdkHelper.initAppFlyerSdk("afKey", new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {

            }

            @Override
            public void onConversionDataFail(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {

            }

            @Override
            public void onAttributionFailure(String s) {

            }
        }, getApplication());
        initAdConfig();

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SdkHelper.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SdkHelper.onPause(this);
    }

    private void initView()
    {
        View btnVideoAd = findViewById(R.id.buttonRewardAd);
        btnVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示激励视频，传入的参数就是ad_setting中的id值
                EyuAdManager.getInstance().showRewardedVideoAd(MainActivity.this, "reward_ad");
            }
        });

        View btnInterstitialAd = findViewById(R.id.btnInterstitialAd);
        btnInterstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示插屏，传入的参数就是ad_setting中的id值
                EyuAdManager.getInstance().showInterstitialAd(MainActivity.this, "inter_ad");
            }
        });
    }

    private void initAdConfig() {
        AdConfig adConfig = new AdConfig();

        //游戏内广告位配置，每个广告位对应一个广告缓存池（cacheGroup)
        adConfig.setAdPlaceConfigStr(SdkHelper.readRawString(this,R.raw.ad_setting));
        //广告key
        adConfig.setAdKeyConfigStr(SdkHelper.readRawString(this,R.raw.ad_key_setting));
        //广告缓存池（cacheGroup）
        adConfig.setAdGroupConfigStr(SdkHelper.readRawString(this,R.raw.ad_cache_setting));

        adConfig.setTtClientId("TtClientId");
        adConfig.setAppName("test");
//        adConfig.setMaxTryLoadRewardAd(2);
//        adConfig.setMaxTryLoadNativeAd(2);
//        adConfig.setMaxTryLoadInterstitialAd(2);

        EyuAdManager.getInstance().config(this, adConfig, this);
    }

    @Override
    public void onAdReward(String type, String placeId) {

    }

    @Override
    public void onAdLoaded(String type, String placeId) {

    }

    @Override
    public void onAdShowed(String type, String placeId) {

    }

    @Override
    public void onAdClosed(String type, String placeId) {

    }

    @Override
    public void onAdClicked(String type, String placeId) {

    }

    @Override
    public void onDefaultNativeAdClicked() {

    }

    @Override
    public void onAdLoadFailed(String placeId, String key, int code) {

    }

    @Override
    public void onImpression(String type, String placeId) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SdkHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}