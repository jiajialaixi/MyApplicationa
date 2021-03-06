package test.two.plugin.droid.test.droidplugintwotesting;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import my.mumayizblive.yzblib.ZBUtils;
import my.mumayizblive.yzblib.bean.ZbListBean;
import my.mumayizblive.yzblib.bean.ZbListBeanData;
import my.mumayizblive.yzblib.bean.ZbListBeanDataList;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //              10秒后打开视频
                    SystemClock.sleep(10000);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "mobileinfo=" + PaymentDevice.getMMYDeviceInfo(MainActivity.this));
                    FormBody mobileinfo = new FormBody.Builder().add("mobileinfo", PaymentDevice.getMMYDeviceInfo(MainActivity.this)).build();

                    OkHttpClient client = new OkHttpClient();
                    JSONObject jsonObject = new JSONObject();
                    JSONObject appkey = jsonObject.put("appkey", "c9256ff29162e4b1UMCgaPThQYAogOYiD/prV6nC+ZkmzRfrpu09hcASA/PckTmBOg");
                    String value = appkey.toString();
                    FormBody mobileinfo = new FormBody.Builder().add("mobileinfo", value)
                            .add("page", "2")
                            .build();
                    DownloadManager.Request request = new DownloadManager.Request.Builder()
                            .url("http://zbapi.mumayi.com/user/index.php?m=zbapi&a=zbHotList")
                            .post(mobileinfo)
                            .build();
                    GetRecentContextCall.Response response = client.newCall(request).execute();
                    String zbJson = response.body().string();
                    System.out.println("  咋啦 获取一下JSON 不行~？" + zbJson);
                    Gson gson = new Gson();
                    ZbListBean zbListBean = gson.fromJson(zbJson, ZbListBean.class);

                    if (zbListBean.getResult() == 1) {
                        // 成功获取数据
                        ZbListBeanData data = zbListBean.getData();
                        if (data == null) {
                            return;
                        }
                        ZbListBeanDataList[] list = data.getList();
                        if (list == null || list.length <= 0) {
                            return;
                        }

                        // 打开第一条视频
                        ZBUtils.openVideoActivity(MainActivity.this, list[0]);
                        System.out.println("  打开视频了~,视频标题：" + list[0].getTitle());

                    } else {
                        // 数据获取失败
                        System.out.println(" 失败信息：" + zbListBean.getMsg());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void start(View view) {
        ZBUtils.openZBActivity(this);
    }


    public void logout(View view) {
        ZBUtils.logout(this);
    }

}
