package com.eyu.app_overseas_new;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eyu.opensdk.ad.AdConfig;
import com.eyu.opensdk.ad.AdPlatform;
import com.eyu.opensdk.ad.EyuAdManager;
import com.eyu.opensdk.ad.EyuAdsListener;
import com.eyu.opensdk.ad.base.model.AdFormat;
import com.eyu.opensdk.ad.base.model.PlatformExtras;
import com.eyu.opensdk.ad.mediation.pangle.PangleExtras;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdConfig();
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

    private void initView() {

        findViewById(R.id.btnLoadRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //手动加载激励视频
                EyuAdManager.getInstance().loadAd(AdFormat.REWARDED, "reward_ad");
            }
        });

        findViewById(R.id.btnShowRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示激励视频
                EyuAdManager.getInstance().show(AdFormat.REWARDED, MainActivity.this, "reward_ad");
            }
        });

        findViewById(R.id.btnLoadInterAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加载插屏
                EyuAdManager.getInstance().loadAd(AdFormat.INTERSTITIAL, "main_view_inter_ad");
            }
        });

        findViewById(R.id.btnShowInterAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示插屏
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
                //展示原生
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
        adConfig.setDebugMode(BuildConfig.DEBUG);
        adConfig.setAdPlaceConfigResource(this, R.raw.and_ad_setting);
        adConfig.setAdKeyConfigResource(this, R.raw.and_ad_key_setting);
        adConfig.setAdGroupConfigResource(this, R.raw.and_ad_cache_setting);
        //admob
        Bundle bundle = new Bundle();
        //appid 在Manifest中配置
        //添加测试设备
//        bundle.putStringArrayList(PlatformExtras.COMMON_TEST_DEVICE, new ArrayList<String>(Arrays.asList("")));
        adConfig.addPlatformConfig(AdPlatform.ADMOB, bundle);

//facebook
        bundle = new Bundle();
//appid 在Manifest中配置
        //添加测试设备
//        bundle.putString(PlatformExtras.COMMON_TEST_DEVICE, "");
        adConfig.addPlatformConfig(AdPlatform.FACEBOOK, bundle);

//max
//appid 在Manifest中配置

//穿山甲
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        bundle.putString(PangleExtras.APP_NAME, "");
        adConfig.addPlatformConfig(AdPlatform.PANGLE, bundle);

//unity
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        adConfig.addPlatformConfig(AdPlatform.UNITY, bundle);

//vungle
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        adConfig.addPlatformConfig(AdPlatform.VUNGLE, bundle);

//mtg
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        bundle.putString(PlatformExtras.COMMON_APP_KEY, "");
        adConfig.addPlatformConfig(AdPlatform.MTG, bundle);

//TRADPLUS
        bundle = new Bundle();
        bundle.putString(PlatformExtras.COMMON_APP_ID, "");
        adConfig.addPlatformConfig(AdPlatform.TRADPLUS, bundle);
        EyuAdManager.getInstance().config(MainActivity.this, adConfig, new EyuAdsListener() {

            @Override
            public void onAdReward(AdFormat adFormat, String s) {
                //激励视频获得奖励
                //type=adFormat.getLabel();
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

}
