package com.zhang.administrator.thermal.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/5/19.
 */
public class AboutProductActivity extends Activity implements View.OnClickListener {

    private ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about_product);

        initView();
        initListener();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.About_back);


    }

    private void initListener() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.About_back:
                startActivity(new Intent(AboutProductActivity.this, SetActivity.class));
                break;
        }
    }
}
