package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/5/19.
 */
public class AboutProductActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "AboutProductActivity";
    private TextView mBack;
    private TextView mTitle;
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
        mPrivacyTv = findViewById(R.id.tv_app_privacy);


    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mPrivacyTv.setOnClickListener(this);
    }

    private void initData() {
        mTitle.setText("关于");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_back:
                finish();
                break;
            case R.id.tv_app_privacy:
                // TODO: 2021/11/14  隐私政策显示

                break;
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AboutProductActivity.class);
        context.startActivity(intent);
    }
}
