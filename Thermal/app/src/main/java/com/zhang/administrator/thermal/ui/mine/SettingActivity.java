package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.acitivity.MainActivity;

/**
 * Created by Administrator on 2016/4/6.
 */
public class SettingActivity extends Activity{
    private RelativeLayout rl_Login;
    private ImageView mBack;
    private TextView mFeedBack;
    private RelativeLayout mAboutProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_set);

        initView();
    }

    private void initView() {
        rl_Login= (RelativeLayout) findViewById(R.id.rl_login);
        mBack= (ImageView) findViewById(R.id.SET_back);
        mFeedBack = (TextView) findViewById(R.id.feedback);
        mAboutProduct = (RelativeLayout) findViewById(R.id.rl_about_product);


        rl_Login.setOnClickListener(new OnClickListenerImpl());
        mBack.setOnClickListener(new OnClickListenerImpl());
        mFeedBack.setOnClickListener(new OnClickListenerImpl());
        mAboutProduct.setOnClickListener(new OnClickListenerImpl());
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.SET_back:
                    startActivity(new Intent(SettingActivity.this, MainActivity.class));
                    finish();
                    break;
                case R.id.rl_login:
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                    finish();
                    break;
                case R.id.feedback:
                    startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
                    break;
                case R.id.rl_about_product:
                    startActivity(new Intent(SettingActivity.this, AboutProductActivity.class));
                    break;


            }
        }
    }
}
