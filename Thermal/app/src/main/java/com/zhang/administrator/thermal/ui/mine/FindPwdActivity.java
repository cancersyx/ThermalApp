package com.zhang.administrator.thermal.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/11/22
 * 设置密保界面+找回密码界面
 */
public class FindPwdActivity extends BaseActivity {
    private TextView mBack, mTitle;
    private EditText mValidateNameEt, mUserNameEt;
    private Button mValidateBtn;
    private TextView mResetPwdTv, mUserNameTv;

    //判断是从设置密保界面跳转进来还是从登录界面跳转进来
    private String mFromActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        mFromActivity = getIntent().getStringExtra("from");
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mValidateNameEt = findViewById(R.id.et_validate_name);
        mUserNameEt = findViewById(R.id.et_user_name);
        mUserNameTv = findViewById(R.id.tv_user_name);
        mResetPwdTv = findViewById(R.id.tv_reset_password);

        if ("security".equals(mFromActivity)) {
            mTitle.setText("设置密保");
        } else {
            mTitle.setText("找回密码");
            mUserNameTv.setVisibility(View.VISIBLE);
            mUserNameEt.setVisibility(View.VISIBLE);
        }
    }

    private void initEvent() {
        mBack.setOnClickListener(v -> finish());
        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initData() {
    }
}
