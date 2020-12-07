package com.eyu.app_overseas_new;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eyu.opensdk.ad.EyuAdManager;
import com.eyu.opensdk.ad.base.listener.EyuAdsListener;
import com.eyu.opensdk.ad.base.model.AdConfig;
import com.eyu.opensdk.ad.base.model.AdFormat;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSDK();
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getUIDs();
            }
        }).start();
    }

    void getUIDs() {
        try {
            AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this);
            String myId = adInfo != null ? adInfo.getId() : null;
            Log.i("AdvertisingId", myId);
            Looper.prepare();
            Toast.makeText(this, "测试fb时，将AdvertisingId{" + myId + "}加入测试", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "error occured " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            Looper.loop();
        }
    }

    private void initSDK() {
        initAdConfig();
    }

    private void initView() {

        findViewById(R.id.btnLoadRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().loadAd(AdFormat.REWARDED, "reward_ad");
            }
        });

        findViewById(R.id.btnShowRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().show(AdFormat.REWARDED, MainActivity.this, "reward_ad");
            }
        });

        findViewById(R.id.btnLoadInterAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().loadAd(AdFormat.INTERSTITIAL, "main_view_inter_ad");
            }
        });

        findViewById(R.id.btnShowInterAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().show(AdFormat.INTERSTITIAL, MainActivity.this, "main_view_inter_ad");
            }
        });

        findViewById(R.id.btnLoadNativeAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().loadAd(AdFormat.NATIVE, "page_view_native_ad");
            }
        });

        findViewById(R.id.btnShowNativeAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().show(AdFormat.NATIVE, MainActivity.this, (ViewGroup) findViewById(R.id.nativeRoot), "page_view_native_ad");
            }
        });

        findViewById(R.id.btnLoadInterRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().loadAd(AdFormat.REWARDED_INTERSTITIAL, "inter_reward_ad");
            }
        });

        findViewById(R.id.btnShowInterRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EyuAdManager.getInstance().show(AdFormat.REWARDED_INTERSTITIAL, MainActivity.this, "inter_reward_ad");
            }
        });
    }

    private void initAdConfig() {
        AdConfig adConfig = new AdConfig();
        adConfig.setAdPlaceConfigResource(this, R.raw.and_ad_setting);
        adConfig.setAdKeyConfigResource(this, R.raw.and_ad_key_setting);
        adConfig.setAdGroupConfigResource(this, R.raw.and_ad_cache_setting);
//        adConfig.setAdmobTestDeviceIds(Arrays.asList("68F0142924806103623C22CBA2697DB1"));
//        adConfig.setFacebookTestDeviceId("bd1dbca5-8ae6-43e5-949b-44fe9b5fdc4c");
        adConfig.setReportEvent(true);
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

}
