package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/4/6.
 * 金相组织界面
 */
public class JinXiangZhuZhi_Activity extends Activity implements View.OnClickListener {
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_jinxiangzhuzhi);

        initView();
        initListener();
    }



    private void initView() {
        mBack = (ImageView) findViewById(R.id.IB_back);

    }

    private void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.IB_back:
                startActivity(new Intent(JinXiangZhuZhi_Activity.this,MainActivity.class));
                finish();
                break;
        }
    }
}
