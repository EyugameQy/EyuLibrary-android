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
    //必须
    implementation 'com.eyu.opensdk:core-ch:1.7.14'
    //广告平台
    implementation 'com.eyu.opensdk.ad.mediation:adapter-all-ch:1.7.14'
}

```
#### 单独引用某个广告平台
```groovy
//必须
implementation 'com.eyu.opensdk:core-ch:1.7.14'
//也可以单独引入某一个广告平台
//implementation 'com.eyu.opensdk.ad.mediation:mtg-ch-adapter:13.0.41.14'
//implementation 'com.eyu.opensdk.ad.mediation:pangle-ch-adapter:3.3.0.3.14'
//implementation 'com.eyu.opensdk.ad.mediation:gdt-adapter:4.294.1164.14

``` 
#### 头条的库需要单独引入
将头条的库拷贝到工程目录的libs下，头条库在这里[app_ch_new](https://github.com/EyugameQy/EyuLibrary-android/tree/master/app_ch_new/libs)

#### 清单文件
```xml
<!--穿山甲-->
<provider
    android:name="com.bytedance.sdk.openadsdk.TTFileProvider"
    android:authorities="${applicationId}.TTFileProvider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/eyu_tt_file_path" />
</provider>
<!--mtg-->
<provider
    android:name="com.mintegral.msdk.base.utils.MTGFileProvider"
    android:authorities="${applicationId}.mtgFileProvider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/eyu_mtg_file_path"/>
</provider>
<!--广点通-->
<provider
    android:name="com.qq.e.comm.GDTFileProvider"
    android:authorities="${applicationId}.gdt.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
     android:name="android.support.FILE_PROVIDER_PATHS"
     android:resource="@xml/eyu_gdt_file_path" />
</provider>
```

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
//广告位
adConfig.setAdPlaceConfigResource(this, R.raw.ad_setting);
//adConfig.setAdPlaceConfigStr

adConfig.setAdKeyConfigResource(this, R.raw.ad_key_setting);
//adConfig.setAdKeyConfigStr()

adConfig.setAdGroupConfigResource(this, R.raw.ad_cache_setting);
//adConfig.setAdGroupConfigStr

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
//banner，需要传入一个ViewGroup，这个group是用来放banner的
EyuAdManager.getInstance().show(AdFormat.BANNER, Activity,ViewGroup,"adPlaceId");
//原生广告，需要传入一个ViewGroup，这个group是用来放native的
EyuAdManager.getInstance().show(AdFormat.NATIVE, Activity,ViewGroup,"adPlaceId");

//show之前可以判断是否有缓存的广告
EyuAdManager.getInstance().isAdLoaded(AdFormat,"adPlaceId")
```

### 事件埋点
#### 基本数据埋点
```java
//事件不带参数
EventHelper.getInstance().logEvent("事件名称");
//事件带map参数
EventHelper.getInstance().logEventWithParamsMap("事件名称",new HashMap<String, Object>());
//事件带json字符串参数
EventHelper.getInstance().logEventWithJsonParams("事件名称","json");
```

#### 数数数据统计
通过EventHelper.getInstance()调用，[文档](https://docs.thinkingdata.cn/ta-manual/latest/installation/installation_menu/client_sdk/android_sdk_installation/android_sdk_installation.html#%E4%B8%89%E3%80%81%E5%8F%91%E9%80%81%E4%BA%8B%E4%BB%B6)
<br>
封装了以下方法

```java
    void track(String var1);

    void track(String var1, JSONObject var2);

    void timeEvent(String var1);

    void login(String var1);

    void logout();

    void identify(String var1);

    void user_set(JSONObject var1);

    void user_setOnce(JSONObject var1);

    void user_add(JSONObject var1);

    void user_append(JSONObject var1);

    void user_add(String var1, Number var2);

    void user_delete();

    void user_unset(String... var1);

    void setSuperProperties(JSONObject var1);

    void trackAppInstall();

    void trackFirst(String var1, JSONObject var2);

    void trackUpdate(String var1, JSONObject var2, String var3);
```

### 配置appsflyer
[https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android](https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android)


