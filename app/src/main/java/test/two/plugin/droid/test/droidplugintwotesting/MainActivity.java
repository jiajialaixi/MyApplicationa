package test.two.plugin.droid.test.droidplugintwotesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yzblib.ZBUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        ZBUtils.openZBActivity(this);
    }


    public void logout(View view) {
        ZBUtils.logout(this);
    }
}
