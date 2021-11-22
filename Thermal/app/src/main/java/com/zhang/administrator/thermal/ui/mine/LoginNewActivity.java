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
import com.zhang.administrator.thermal.util.MD5Utils;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2016/4/25.
 */
public class LoginNewActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBack;
    private TextView mTitle;
    private EditText mUserNameEt, mPwdEt;
    private Button mBtnLogin;
    private TextView mRegister;
    private TextView mFindPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        initView();
        initListener();
    }


    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mUserNameEt = (EditText) findViewById(R.id.et_username);
        mPwdEt = (EditText) findViewById(R.id.et_input_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mRegister = (TextView) findViewById(R.id.tv_register);
        mFindPwd = (TextView) findViewById(R.id.tv_forget_pwd);

        mTitle.setText("登录");
    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mFindPwd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_back:
                finish();
                break;
            case R.id.tv_register:
                RegisterActivity.startActivityForResult(this, 100);
                break;
            case R.id.tv_forget_pwd:
                // TODO: 2021/11/22  找回密码

                break;
            case R.id.btn_login:
                String username = mUserNameEt.getText().toString();
                String password = mPwdEt.getText().toString();
                String pwdMd5 = MD5Utils.md5(password);
                String pwdSp = readPasswordSp(username);

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginNewActivity.this, "请输入用户名。", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginNewActivity.this, "请输入密码。", Toast.LENGTH_LONG).show();
                    return;
                } else if (pwdMd5.equals(pwdSp)) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    //保存登录状态和用户名
                    saveLoginStatus(true, username);
                    //登录成功状态传递到MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("is_login", true);
                    setResult(RESULT_OK, intent);
                    finish();
                    return;
                } else if (!TextUtils.isEmpty(pwdSp) && !pwdMd5.equals(pwdSp)) {
                    Toast.makeText(this, "输入的用户名不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(this, "此用户不存在", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    /**
     * 保存登录状态和用户名到SharedPreferences
     *
     * @param status
     * @param userName
     */
    private void saveLoginStatus(boolean status, String userName) {
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_login", status);
        editor.putString("login_user_name", userName);//存入登录时候用户名
        editor.commit();
    }

    /**
     * 根据用户名读取SharedPreferences保存的密码
     *
     * @param userName
     * @return
     */
    private String readPasswordSp(String userName) {
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        return sharedPreferences.getString(userName, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //注册界面传递过来的数据
            String userName = data.getStringExtra("user_name");
            if (!TextUtils.isEmpty(userName)) {
                mUserNameEt.setText(userName);
                mUserNameEt.setSelection(userName.length());
            }
        }
    }

    @Deprecated
    public static void startActivity(Context context, String userName, String password) {
        Intent intent = new Intent(context, LoginNewActivity.class);
        intent.putExtra("user_name", userName);
        intent.putExtra("user_password", password);
        context.startActivity(intent);
    }


}
