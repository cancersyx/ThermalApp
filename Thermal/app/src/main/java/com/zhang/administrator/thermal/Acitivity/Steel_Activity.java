package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.Tools.TxtReader;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Steel_Activity extends Activity implements View.OnClickListener {

    private ImageView IB_back;
    private TextView tv_steel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_steel);
        initView();
        showIron();

    }

    private void initView() {
        IB_back= (ImageView) findViewById(R.id.IB_back);
        tv_steel= (TextView) findViewById(R.id.tv_steel);


        IB_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.IB_back:
                startActivity(new Intent(Steel_Activity.this,MetalCategory_Activity.class));
                break;
        }

    }

    private void showIron() {
        InputStream inputStream = getResources().openRawResource(R.raw.steel);
        String string = TxtReader.getString(inputStream);
        tv_steel.setText(string);
    }
}
