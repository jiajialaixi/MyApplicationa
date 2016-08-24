# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android_SDK\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5
-dontskipnonpubliclibraryclassmembers
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#-ignorewarnings
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep public class * extends android.os.IInterface

-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#-------------------------------------------------
# 以上属于混淆文件的默认配置，应该是每个项目都会有的
#-------------------------------------------------

#==== 一直播sdk的混淆配置 ====================
-dontwarn my.mumayizblive.yzblib.**
-keep class my.mumayizblive.yzblib.** { *;}

-dontwarn com.mumayi.droidplugin.**
-keep class com.mumayi.droidplugin.** { *;}

-dontwarn com.mumayi.helper.**
-keep class com.mumayi.helper.** { *;}

-dontwarn com.mumayi.**
-keep class com.mumayi.** { *;}

-dontwarn android.util.Singleton.**
-keep class android.util.Singleton.** { *;}

-dontwarn android.util.**
-keep class android.util.** { *;}
#==== 一直播sdk的混淆配置完毕 ===================


# okhttp的混淆配置
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-keep class okhttp3.** { *;}
-keep class okio.** { *;}
-dontwarn sun.security.**
-keep class sun.security.** { *;}
-dontwarn okio.**
-dontwarn okhttp3.**

#gson的混淆配置
-keep class com.google.gson.** {*;}