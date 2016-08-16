package test.two.plugin.droid.test.droidplugintwotesting;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.widget.Toast;

import com.example.yzblib.ZBGetAppKeyListener;
import com.example.yzblib.ZBLoadResultCallback;
import com.example.yzblib.ZBUtils;


/**
 * Created by Administrator on 2016/8/4.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZBUtils.init(this, new ZBLoadResultCallback() {
            @Override
            public void success() {
                System.out.println(" 初始化成功");

                ZBUtils.getAppKey(MyApplication.this, new ZBGetAppKeyListener() {
                    @Override
                    public void getAppKey(String appKey) {
                        System.out.println(" 加载成功 获取 appKey:" + appKey);
                    }
                });
//                        SharePrefUtil.saveString(MyApplication.this, "zb_mumayi_appkey", "45678979845466611");
                ZBUtils.setAppKey(MyApplication.this, "c9256ff29162e4b1UMCgaPThQYAogOYiD/prV6nC+ZkmzRfrpu09hcASA/PckTmBOg");
                System.out.println("  加载成功 设置appKey");
                ZBUtils.getAppKey(MyApplication.this, new ZBGetAppKeyListener() {
                    @Override
                    public void getAppKey(String appKey) {
                        System.out.println(" 加载成功 获取 appKey2:" + appKey);
                    }
                });
            }

            @Override
            public void failed(String s) {
            }
        });


        registerReceiver(new ShareBraodcastReceiver(), new IntentFilter("com.example.administrator.zbsimple" + "ACTION_START_SHARE_ACTIVITY"));

    }

    class ShareBraodcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("  收到分享信息了");
//            int isfocus = 0;
//            try {
//                isfocus = intent.getIntExtra("isfocus", 0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            String playurl = intent.getStringExtra("playurl");
//            String playurl = "0.0-" + isfocus;
            System.out.println("  收到分享信息了：" + playurl);
            Toast.makeText(context, "收到分享了" + playurl, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        ZBUtils.initAttachBaseContext(base);
        super.attachBaseContext(base);
    }
}
