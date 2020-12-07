[https://github.com/EyugameQy/EyuLibrary-android.git](https://github.com/EyugameQy/EyuLibrary-android.git)
================
### 迁移到 AndroidX（特别需要注意原生广告）
重构命令使用两个标记。默认情况下，这两个标记在 gradle.properties 文件中都设为 true：

android.useAndroidX=true
Android 插件会使用对应的 AndroidX 库而非支持库。
android.enableJetifier=true
类库映射：
https://developer.android.com/jetpack/androidx/migrate/artifact-mappings
类的对应关系请参考：
https://developer.android.com/jetpack/androidx/migrate/class-mappings

### 配置multiDexEnabled
[https://developer.android.com/studio/build/multidex](https://developer.android.com/studio/build/multidex)

### 配置google-services.json
从firebase控制台下载 google-services.json ，并复制到module根目录下

### 项目的build.gradle增加以下内容
```gradle
buildscript {
    repositories {
       maven {
           url  "https://dl.bintray.com/mintegral-official/mintegral_ad_sdk_android_for_oversea"
       }
        maven { url "https://fyber.bintray.com/marketplace" }
        maven { url "https://dl.bintray.com/ironsource-mobile/android-sdk" }
    }
    dependencies {
        classpath 'com.google.gms:google-services:4.2.0'
        classpath 'com.google.firebase:firebase-crashlytics-gradle:2.4.1'
    }
}

allprojects {
    repositories {
        maven {
            url 'https://repo.rdc.aliyun.com/repository/74503-release-qNEqtU/'
            credentials {
                username 'p5gXfa'
                password 'wKY0RHNSH3'
            }
        }
        maven { url "https://jitpack.io" }//
         maven {
             url  "https://dl.bintray.com/mintegral-official/mintegral_ad_sdk_android_for_oversea"
         }
        maven { url "https://fyber.bintray.com/marketplace" }
        maven { url "https://dl.bintray.com/ironsource-mobile/android-sdk" }
    }
}
```
### app module的build.gradle 添加以下内容
```gradle
apply plugin: 'com.google.gms.google-services'
apply plugin: 'com.google.firebase.crashlytics'

dependencies {
    implementation 'androidx.multidex:multidex:2.0.1'
    implementation 'com.eyu.opensdk:core:1.7.1'
    implementation 'com.eyu.opensdk.ad.mediation:adapter-all:1.7.1'
}
```

### SDK使用
#### 初始化sdk
```java
//添加配置信息，按需添加
InitializerBuilderImpl builder = new InitializerBuilderImpl();
//appsflyer配置
//builder.initAppsFlyer(key);

//ThinkData
//builder.initThinkData("","");

//远程配置
//Map<String, Object> defaultsMap = new HashMap<>();
//defaultsMap.put("key","defaultValue");
//builder.initRemoteConfig(sDefaultsMap);

SdkCompat.getInstance().init(app, builder);

```

#### 广告配置与初始化
#### 配置
广告配置有三个文件，一般放在res/raw下，具体见[demo app_overseas](https://github.com/EyugameQy/EyuLibrary-android/tree/master/app_overseas)，分别的含义如下：
##### ad_setting.json
广告位配置
```json
[
    {
     "cacheGroup": "main_view_inter_ad",
     "isEnabled": "true",
     "nativeAdLayout": "",
     "id": "main_view_inter_ad",
     "desc": "首页插屏"
    },
    {
    "cacheGroup": "page_view_native_ad",
    "isEnabled": "true",
    "nativeAdLayout": "native_ad_in_page",//nativeAdLayout是原生广告的容器，value是native_ad_in_page，对应layout中 native_ad_in_page.xml
    "id": "page_view_native_ad",
    "desc": "页面原生广告"
    }
]
```
##### ad_cache_setting.json
广告的缓存池配置
```json
  {
    "keys": "[\"fb_ys_a\",\"adys_sy\"]",
    "isAutoLoad": "true",
    "id": "page_view_native_ad",
    "type": "nativeAd"
  }
```
##### ad_key_setting.json
各个广告平台的key，admob测试时，可以将其替换为对应的测试key，打包的时候替换回来即可
```json
[
 {"id":"adcp_js","key":"ca-app-pub-3940256099942544/1033173712","network":"admob"},
 {"id":"adjl_jsg","key":"ca-app-pub-3940256099942544/5224354917","network":"admob"},
 {"id":"adys_sy","key":"ca-app-pub-3940256099942544/2247696110","network":"admob"},
 {"id":"adys_ba","key":"ca-app-pub-3940256099942544/6300978111","network":"admob"}
]
```

#### 初始化
```java
AdConfig adConfig = new AdConfig();
adConfig.setAdPlaceConfigResource(this, R.raw.ad_setting);
//adConfig.setAdPlaceConfigStr

adConfig.setAdKeyConfigResource(this, R.raw.ad_key_setting);
//adConfig.setAdKeyConfigStr()

adConfig.setAdGroupConfigResource(this, R.raw.ad_cache_setting);
//adConfig.setAdGroupConfigStr

//测试设备配置，获取方式，参考后面的"测试"
//adConfig.setAdmobTestDeviceIds(Arrays.asList("68F0142924806103623C22CBA2697DB1"));

//adConfig.setFacebookTestDeviceId("bd1dbca5-8ae6-43e5-949b-44fe9b5fdc4c");

adConfig.setReportEvent(true);
EyuAdManager.getInstance().config(MainActivity.this, adConfig, new EyuAdsListener() {

    @Override
    public void onAdReward(AdFormat type, String placeId) {

    }

    @Override
    public void onAdLoaded(AdFormat type, String placeId) {

    }

    @Override
    public void onAdShowed(AdFormat type, String placeId) {

    }

    @Override
    public void onAdClosed(AdFormat type, String placeId) {

    }

    @Override
    public void onAdClicked(AdFormat type, String placeId) {

    }

    @Override
    public void onDefaultNativeAdClicked() {

    }

    @Override
    public void onAdLoadFailed(AdFormat type, String placeId, String key, int code) {

    }

    @Override
    public void onImpression(AdFormat type, String placeId) {

    }

});
   
```

#### 使用示例
```java
//adPlaceId为ad_setting.json中的id
 //激励视频
EyuAdManager.getInstance().show(AdFormat.REWARDED, Activity,"adPlaceId");
//插屏
EyuAdManager.getInstance().show(AdFormat.INTERSTITIAL, Activity,"adPlaceId");
//banner
EyuAdManager.getInstance().show(AdFormat.BANNER, Activity,ViewGroup,"adPlaceId");
//原生广告
EyuAdManager.getInstance().show(AdFormat.NATIVE, Activity,ViewGroup,"adPlaceId");

//show之前可以判断是否有缓存的广告
EyuAdManager.getInstance().isAdLoaded(AdFormat,"adPlaceId")
```
#### 清单文件中添加配置
```xml
<manifest>
    <application>
        <!--google ads-->
       <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="@string/google_ads_app_id" />
        <!-- facebook ads-->
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id" />
        <!-- applovin max ads-->
        <meta-data
            android:name="applovin.sdk.key"
            android:value="@string/applovin_sdk_key" />

    </application>
</manifest>

        
```

### 配置appsflyer
[https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android](https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android)

### 测试
**强烈建议使用VPN挂到美国测试**，没有广告请检查日志打印，过滤onAdLoadFailed,一般失都是广告没有填充
#### 谷歌
##### 使用示例广告单元
广告格式	示例广告单元 ID <br>
横幅广告	ca-app-pub-3940256099942544/6300978111 <br>
插页式广告	ca-app-pub-3940256099942544/1033173712 <br> 
插页式视频广告	ca-app-pub-3940256099942544/8691691433 <br> 
激励视频广告	ca-app-pub-3940256099942544/5224354917 <br> 
原生高级广告	ca-app-pub-3940256099942544/2247696110 <br> 
原生高级视频广告	ca-app-pub-3940256099942544/1044960115 <br> 

##### 使用测试设备
如果您希望使用实际投放的广告进行更严格的测试，那么您现在可以将您的设备配置为测试设备
系统会自动将 Android 模拟器配置为测试设备
检查 logcat 输出，过滤addTestDevice查找设备id，并加入的初始化的代码当中

```
I/Ads: Use AdRequest.Builder.addTestDevice("68F0142924806103623C22CBA2697DB1") to get test ads on this device.
```
重新运行您的应用。如果广告是 Google 广告，则您会在广告（横幅广告、插页式广告或激励视频广告）顶部的中间部分看到一个“测试广告”标签：

#### Facebook
检测logcat输出，过滤"Test mode device hash"，将其添加到初始化配置


