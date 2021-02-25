package com.eyu.app_quick_start;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.eyu.opensdk.ad.base.model.AdFormat;
import com.eyu.opensdk.integration.quick_start.ActivityStarter;
import com.eyu.opensdk.integration.quick_start.SDKHelper;

public class MainActivity extends AppCompatActivity implements ActivityStarter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        findViewById(R.id.btnLoadRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //手动加载激励视频
                SDKHelper.loadAd(AdFormat.REWARDED, "reward_ad");
            }
        });

        findViewById(R.id.btnShowRewardAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示激励视频
                SDKHelper.showReward();
            }
        });

        findViewById(R.id.btnLoadInterAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加载插屏
                SDKHelper.loadAd(AdFormat.INTERSTITIAL,"");
            }
        });

        findViewById(R.id.btnShowInterAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示插屏
                SDKHelper.showInter();
            }
        });
    }


}