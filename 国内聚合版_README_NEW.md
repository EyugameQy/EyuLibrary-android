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

### 项目的buld.gradle增加以下内容
```gradle
buildscript {
    ...
    repositories {
        ...
        maven { url 'https://dl.bintray.com/umsdk/release' }
        ...
    }
    ...
}
allprojects {
    repositories {
        ...
        maven {
            url 'https://repo.rdc.aliyun.com/repository/74503-release-qNEqtU/'
            credentials {
                username 'p5gXfa'
                password 'wKY0RHNSH3'
            }
        }
        maven { url 'https://dl.bintray.com/umsdk/release' }
        ...
    }
}
```
### module的build.gradle 添加以下内容
```gradle
repositories {
    flatDir {
        dirs 'libs'
    }
}
dependencies {
    compile(name: 'open_ad_sdk', ext: 'aar')
    implementation 'androidx.multidex:multidex:2.0.1'
    implementation 'com.eyu.opensdk:core-ch:1.7.1'
    implementation 'com.eyu.opensdk.ad.mediation:adapter-all-ch:1.7.2'
}

```
### 头条的库需要单独引入
将头条的库拷贝到工程目录的libs下，[app_ch_new](https://github.com/EyugameQy/EyuLibrary-android/tree/master/app_ch_new/src/libs）

### SDK使用
#### 初始化sdk
```java
//在Application中初始化，添加配置信息，按需添加
InitializerBuilderImpl builder = new InitializerBuilderImpl();
//appsflyer
//builder.initAppsFlyer("key");
//热云
//builder.initTracking(this,"appKey","channle");
//友盟
//builder.initUmeng("appKey","channle");

//builder.initThinkData("key","serverUrl");
SdkCompat.getInstance().init(Application, builder);

```

#### 广告配置与初始化
#### 配置
广告配置有三个文件，一般放在res/raw下，具体见[demo app_ch_new](https://github.com/EyugameQy/EyuLibrary-android/tree/master/app_overseas)，分别的含义如下：
##### ad_setting.json
广告位配置
```json
[
    {
     "cacheGroup": "main_view_inter_ad",//缓存池id
     "isEnabled": "true",
     "nativeAdLayout": "",
     "id": "main_view_inter_ad",//adPlaceId
     "desc": "首页插屏"
    }
]
```
##### ad_cache_setting.json
广告的缓存池配置
##### ad_key_setting.json
各个广告平台的key

#### 权限申请
```java
 String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION};
SdkCompat.getInstance().requestPermissions(this, permissions, 1000);
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
#### 生命周期处理
```java

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

```
#### 权限回调
```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    SdkCompat.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
}
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

### 配置appsflyer
[https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android](https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android)


