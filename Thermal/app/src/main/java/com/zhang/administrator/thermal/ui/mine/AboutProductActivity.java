package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zhang.administrator.thermal.ui.PrivacyActivity;
import com.zsf.common.ApkUtil;

/**
 * Created by Administrator on 2016/5/19.
 */
public class AboutProductActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "AboutProductActivity";
    private TextView mBack;
    private TextView mTitle;
    private TextView mAppName, mAppVersion;
    private TextView mPrivacyTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about_product);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mAppName = findViewById(R.id.tv_app_name);
        mAppVersion = findViewById(R.id.tv_app_version);
        mPrivacyTv = findViewById(R.id.tv_app_privacy);


    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mPrivacyTv.setOnClickListener(this);
    }

    private void initData() {
        mTitle.setText("关于");
        String versionName = ApkUtil.getVersionName(this, this.getPackageName());
        mAppVersion.setText("v_"+versionName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_back:
                finish();
                break;
            case R.id.tv_app_privacy:
                PrivacyActivity.startActivity(this);
                break;
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AboutProductActivity.class);
        context.startActivity(intent);
    }
}
