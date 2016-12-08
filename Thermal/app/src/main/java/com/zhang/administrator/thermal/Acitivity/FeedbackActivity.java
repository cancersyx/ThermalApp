package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/5/19.
 */
public class FeedbackActivity extends Activity implements View.OnClickListener {

    private ImageView mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feedback);

        initView();
        listener();
    }



    private void initView() {
        mBack = (ImageView) findViewById(R.id.Feed_back);
    }

    private void listener() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Feed_back:
                startActivity(new Intent(FeedbackActivity.this,Set_Activity.class));
                finish();
                break;
        }
    }
}
