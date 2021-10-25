package com.zhang.administrator.thermal.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.util.TxtReader;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/5/5.
 */
public class FourthItemActivity extends Activity {
    private TextView tv_item_first;
    private ImageView  IB_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_item_first);

        tv_item_first= (TextView) findViewById(R.id.tv_item_first);
        IB_back= (ImageView) findViewById(R.id.lx_back);
        IB_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FourthItemActivity.this, LiXueXingNengActivity.class));
            }
        });
        showFirstItem();

    }

    private void showFirstItem() {
        InputStream inputStream = getResources().openRawResource(R.raw.qiangdu);
        String string = TxtReader.getString(inputStream);
        tv_item_first.setText(string);
    }
}
