package com.zhang.administrator.thermal.ui.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zhang.administrator.thermal.ui.MainActivity;

/**
 * Created by Administrator on 2016/4/6.
 */
public class SettingActivity extends BaseActivity {
    public static  SettingActivity instance = null;
    private TextView mBack, mTitle;
    private RelativeLayout mModifyPwd, mSecurity, mExitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);
        instance = this;
        initView();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mModifyPwd = (RelativeLayout) findViewById(R.id.rl_modify_password);
        mSecurity = (RelativeLayout) findViewById(R.id.rl_security_setting);
        mExitLogin = (RelativeLayout) findViewById(R.id.rl_exit_login);

        mTitle.setText("设置");

        mBack.setOnClickListener(new OnClickListenerImpl());
        mModifyPwd.setOnClickListener(new OnClickListenerImpl());
        mSecurity.setOnClickListener(new OnClickListenerImpl());
        mExitLogin.setOnClickListener(new OnClickListenerImpl());
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_title_back:
                    finish();
                    break;
                case R.id.rl_modify_password:
                    ModifyPasswordActivity.startActivity(SettingActivity.this);
                    break;
                case R.id.rl_security_setting:
                    // TODO: 2021/11/22 设置密保 

                    break;
                case R.id.rl_exit_login:
                    Toast.makeText(SettingActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                    clearLoginStatus();
                    Intent intent = new Intent();
                    intent.putExtra("is_login", false);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }
        }
    }


    private void clearLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_login", false);
        editor.putString("login_user_name", "");
        editor.commit();
    }


}
