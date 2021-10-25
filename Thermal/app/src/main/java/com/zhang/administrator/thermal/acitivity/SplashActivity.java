package com.zhang.administrator.thermal.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/4/7.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Message msg=Message.obtain();
        handler.sendMessageDelayed(msg,2000);

    }
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };

}
