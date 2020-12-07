package com.eyu.test;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eyu.opensdk.ad.EyuAdManager;
import com.eyu.opensdk.ad.base.listener.EyuAdsListener;
import com.eyu.opensdk.ad.base.model.AdConfig;
import com.eyu.opensdk.ad.base.model.AdFormat;
import com.eyu.opensdk.core.SdkCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION};
        SdkCompat.getInstance().requestPermissions(this, permissions, 1000);
        initAdConfig();
        initView();
    }


    private void initAdConfig() {
        AdConfig adConfig = new AdConfig();
        adConfig.setAdPlaceConfigResource(this, R.raw.and_ad_setting);
        adConfig.setAdKeyConfigResource(this, R.raw.and_ad_key_setting);
        adConfig.setAdGroupConfigResource(this, R.raw.and_ad_cache_setting);
        adConfig.setReportEvent(true);
        //头条广告
//        adConfig.setTtClientId("TtClientId");
//        adConfig.setAppName("test");
        //Mintegral广告
//        adConfig.setMintegralAppId("AppId");
//        adConfig.setMintegralAppKey("AppKey");
        //广点通
//        adConfig.setGdtAppId("");
        EyuAdManager.getInstance().config(MainActivity.this, adConfig, new EyuAdsListener() {

            @Override
            public void onAdReward(AdFormat adFormat, String s) {

            }

            @Override
            public void onAdLoaded(AdFormat adFormat, String s) {

            }

            @Override
            public void onAdShowed(AdFormat adFormat, String s) {

            }

            @Override
            public void onAdClosed(AdFormat adFormat, String s) {

            }

            @Override
            public void onAdClicked(AdFormat adFormat, String s) {

            }

            @Override
            public void onDefaultNativeAdClicked() {

            }

            @Override
            public void onAdLoadFailed(AdFormat adFormat, String s, String s1, int i) {

            }

            @Override
            public void onImpression(AdFormat adFormat, String s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SdkCompat.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SdkCompat.getInstance().onPause(this);
    }

    private void initView() {
        View btnVideoAd = findViewById(R.id.buttonRewardAd);
        btnVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().show(AdFormat.REWARDED, MainActivity.this, "reward_ad");
            }
        });

        View btnInterstitialAd = findViewById(R.id.btnInterstitialAd);
        btnInterstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().show(AdFormat.INTERSTITIAL, MainActivity.this, "inter_ad");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SdkCompat.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

}