package com.eyu.opensdk.integration.quick_start;

import android.view.ViewGroup;

import com.eyu.opensdk.ad.EyuAdManager;
import com.eyu.opensdk.ad.base.model.AdFormat;

public class SDKHelper {

    private static final String REWARD_AD_PLACE_ID = "reward_ad";
    private static final String INTERSTITIAL_AD_PLACE_ID = "inter_ad";
    private static final String BANNER_AD_PLACE_ID = "banner_ad";


    /**
     * 展示插屏
     */
    public static void showInter() {
        EyuAdManager.getInstance().show(AdFormat.INTERSTITIAL, QuickStarter.getInstance().getActivity(), INTERSTITIAL_AD_PLACE_ID);
    }

    /**
     * 展示激励视频
     */
    public static void showReward() {
        EyuAdManager.getInstance().show(AdFormat.REWARDED, QuickStarter.getInstance().getActivity(), REWARD_AD_PLACE_ID);
    }

    /**
     * 展示banner
     */
    public static void showBanner(ViewGroup viewGroup) {
        EyuAdManager.getInstance().show(AdFormat.BANNER, QuickStarter.getInstance().getActivity(), viewGroup, BANNER_AD_PLACE_ID);
    }

    public static void loadAd(AdFormat adFormat, String adPlaceId) {
        EyuAdManager.getInstance().loadAd(adFormat, adPlaceId);
    }

    public static boolean isAdLoaded(AdFormat adFormat, String adPlaceId) {
        return EyuAdManager.getInstance().isAdLoaded(adFormat, adPlaceId);
    }

    public static boolean isRewardAdLoaded() {
        return EyuAdManager.getInstance().isAdLoaded(AdFormat.REWARDED, REWARD_AD_PLACE_ID);
    }

    public static boolean isInterAdLoaded() {
        return EyuAdManager.getInstance().isAdLoaded(AdFormat.INTERSTITIAL, INTERSTITIAL_AD_PLACE_ID);
    }

    public static boolean isBannerAdLoaded() {
        return EyuAdManager.getInstance().isAdLoaded(AdFormat.BANNER, BANNER_AD_PLACE_ID);
    }
}
