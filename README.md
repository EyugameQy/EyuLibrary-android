https://github.com/EyugameQy/EyuLibrary-android.git

# SDK版本
## 海外版本
```groovy
    //必须
    implementation 'com.eyu.opensdk:core:1.7.3'
    //引入所有广告平台
    implementation 'com.eyu.opensdk.ad.mediation:adapter-all:1.7.3'
    
    //也可以单独引入某一个广告平台
    //implementation 'com.eyu.opensdk.ad.mediation:max-adapter:9.14.10.10'
    //implementation 'com.eyu.opensdk.ad.mediation:admob-adapter:19.5.0.10'
    //implementation 'com.eyu.opensdk.ad.mediation:facebook-adapter:6.2.0.10'
    //implementation 'com.eyu.opensdk.ad.mediation:applovin-adapter:9.14.9.10'
    //implementation 'com.eyu.opensdk.ad.mediation:mtg-adapter:15.2.41.10'
    //implementation 'com.eyu.opensdk.ad.mediation:pangle-adapter:3.1.7.5.10'
    //implementation 'com.eyu.opensdk.ad.mediation:unity-adapter:3.4.8.10'
    //implementation 'com.eyu.opensdk.ad.mediation:vungle-adapter:6.8.1.10'
```
### 接入请参考
https://github.com/EyugameQy/EyuLibrary-android/blob/master/国外聚合版_README_NEW.md
demo工程为app_overseas_new

## 国内版本
```groovy
//必须
implementation 'com.eyu.opensdk:core-ch:1.7.3'
//引入所有平台
implementation 'com.eyu.opensdk.ad.mediation:adapter-all-ch:1.7.3'

//也可以单独引入某一个广告平台
//implementation 'com.eyu.opensdk.ad.mediation:mtg-ch-adapter:13.0.41.10'
//implementation 'com.eyu.opensdk.ad.mediation:pangle-ch-adapter:3.3.0.3.10'
//implementation 'com.eyu.opensdk.ad.mediation:gdt-adapter:4.294.1164.10

```
### 接入请参考
https://github.com/EyugameQy/EyuLibrary-android/blob/master/国内聚合版_README_NEW.md
demo工程为app_ch_new


# 旧版本sdk接入参考
## 国内聚合版本（穿山甲，mtg及广点通）接入请参考
https://github.com/EyugameQy/EyuLibrary-android/blob/master/国内聚合版_README.md
demo工程为app

## 国内头条版本接入请参考
https://github.com/EyugameQy/EyuLibrary-android/blob/master/国内头条版_README.md
demo工程为app_ch

## 国外聚合版本接入请参考
https://github.com/EyugameQy/EyuLibrary-android/blob/master/国外聚合版_README.md
demo工程为app_overseas