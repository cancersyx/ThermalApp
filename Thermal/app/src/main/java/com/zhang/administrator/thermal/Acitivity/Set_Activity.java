package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/4/6.
 */
public class Set_Activity extends Activity{
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
                    startActivity(new Intent(Set_Activity.this,MainActivity.class));
                    finish();
                    break;
                case R.id.rl_login:
                    startActivity(new Intent(Set_Activity.this,Login_Activity.class));
                    finish();
                    break;
                case R.id.feedback:
                    startActivity(new Intent(Set_Activity.this,FeedbackActivity.class));
                    break;
                case R.id.rl_about_product:
                    startActivity(new Intent(Set_Activity.this,AboutProductActivity.class));
                    break;


            }
        }
    }
}
