package com.eyu.test;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eyu.opensdk.ad.AdConfig;
import com.eyu.opensdk.ad.AdPlatform;
import com.eyu.opensdk.ad.EyuAdManager;
import com.eyu.opensdk.ad.EyuAdsListener;
import com.eyu.opensdk.ad.base.model.AdFormat;
import com.eyu.opensdk.ad.base.model.PlatformExtras;
import com.eyu.opensdk.ad.mediation.pangle.PangleExtras;
import com.eyu.opensdk.core.SdkCompat;

import java.util.HashMap;

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
//        EventHelper.getInstance().logEvent("事件名称");
//        EventHelper.getInstance().logEventWithParamsMap("事件名称",new HashMap<String, Object>());
//        EventHelper.getInstance().logEventWithJsonParams("事件名称","json");

    }


    private void initAdConfig() {
        AdConfig adConfig = new AdConfig();
        adConfig.setDebugMode(BuildConfig.DEBUG);
        adConfig.setAdPlaceConfigResource(this, R.raw.and_ad_setting);
        adConfig.setAdKeyConfigResource(this, R.raw.and_ad_key_setting);
        adConfig.setAdGroupConfigResource(this, R.raw.and_ad_cache_setting);

//穿山甲
        Bundle bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        bundle.putString(PangleExtras.APP_NAME, "");
        adConfig.addPlatformConfig(AdPlatform.PANGLE, bundle);

//mtg
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        bundle.putString(PlatformExtras.COMMON_APP_KEY, "");
        adConfig.addPlatformConfig(AdPlatform.MTG, bundle);

//TOPON
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        bundle.putString(PlatformExtras.COMMON_APP_KEY, "");
        adConfig.addPlatformConfig(AdPlatform.TOPON, bundle);

//广点通
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        adConfig.addPlatformConfig(AdPlatform.GDT, bundle);
        EyuAdManager.getInstance().config(MainActivity.this, adConfig, new EyuAdsListener() {

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