package com.zhang.administrator.thermal.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zhang.administrator.thermal.util.AnalysisUtils;
import com.zsf.common.MD5Utils;

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
        mValidateBtn = findViewById(R.id.btn_validate);

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
                String validateName = mValidateNameEt.getText().toString().trim();
                if ("security".equals(mFromActivity)) {
                    //设置密保
                    if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(FindPwdActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(FindPwdActivity.this, "密保设置成功", Toast.LENGTH_SHORT).show();
                        //保存密保到SharedPreference
                        saveSecurity(validateName);
                        finish();
                    }
                } else {
                    //找回密码界面
                    String userName = mUserNameEt.getText().toString().trim();
                    String securitySp = readSecurity(userName);
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(FindPwdActivity.this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!isExistUserName(userName)) {
                        Toast.makeText(FindPwdActivity.this, "您输入的用户名不存在", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(FindPwdActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!validateName.equals(securitySp)) {
                        Toast.makeText(FindPwdActivity.this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //输入的密保正确，重置密码
                        mResetPwdTv.setVisibility(View.VISIBLE);
                        mResetPwdTv.setText("初始密码：123456");
                        savePassword(userName);
                    }
                }
            }
        });

    }

    private void initData() {
    }

    private void saveSecurity(String validateName) {
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this) + "_security", validateName);
        editor.commit();
    }

    /**
     * @param userName
     * @return
     */
    private String readSecurity(String userName) {
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        String security = sharedPreferences.getString(userName + "_security", "");
        return security;
    }

    /**
     * @param userName
     * @return
     */
    private boolean isExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        String pwdSp = sharedPreferences.getString(userName, "");
        if (!TextUtils.isEmpty(pwdSp)) {
            hasUserName = true;
        }
        return hasUserName;
    }

    /**
     * @param userName
     */
    private void savePassword(String userName) {
        String md5Pwd = MD5Utils.md5("123456");
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userName, md5Pwd);
        editor.commit();
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FindPwdActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context,String from) {
        Intent intent = new Intent(context, FindPwdActivity.class);
        intent.putExtra("from",from);
        context.startActivity(intent);
    }
}
