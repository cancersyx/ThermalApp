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
 */
public class ModifyPasswordActivity extends BaseActivity {
    private static final String TAG = "ModifyPasswordActivity";
    private TextView mBack, mTitle;
    private EditText mOriginalPwd, mNewPwd, mNewPwdAgain;
    private Button mSavePwd;
    private String mUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);

        initView();
        initEvent();
        mUserName = AnalysisUtils.readLoginUserName(this);
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mOriginalPwd = findViewById(R.id.et_original_password);
        mNewPwd = findViewById(R.id.et_new_password);
        mNewPwdAgain = findViewById(R.id.et_new_password_again);
        mSavePwd = findViewById(R.id.btn_save_pwd);

        mTitle.setText("修改密码");
    }

    private void initEvent() {
        mBack.setOnClickListener(v -> finish());
        mSavePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String originalPwd = mOriginalPwd.getText().toString().trim();
                String newPassword = mNewPwd.getText().toString().trim();
                String newPasswordAgain = mNewPwdAgain.getText().toString().trim();

                if (TextUtils.isEmpty(originalPwd)) {
                    Toast.makeText(ModifyPasswordActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!MD5Utils.md5(originalPwd).equals(readPwdSp())) {
                    Toast.makeText(ModifyPasswordActivity.this, "输入的密码和原始密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if (MD5Utils.md5(newPassword).equals(readPwdSp())) {
                    Toast.makeText(ModifyPasswordActivity.this, "输入的新密码不能和原始密码一样", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPassword)) {
                    Toast.makeText(ModifyPasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPasswordAgain)) {
                    Toast.makeText(ModifyPasswordActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!newPassword.equals(newPasswordAgain)) {
                    Toast.makeText(ModifyPasswordActivity.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(ModifyPasswordActivity.this, "新密码设置成功", Toast.LENGTH_SHORT).show();
                    modifyPassword(newPassword);
                    Intent intent = new Intent(ModifyPasswordActivity.this, LoginNewActivity.class);
                    startActivity(intent);
                    //关闭设置界面
                    SettingActivity.instance.finish();
                    finish();

                }
            }
        });
    }

    private void modifyPassword(String password) {
        String md5Pwd = MD5Utils.md5(password);
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mUserName, md5Pwd);
        editor.commit();
    }

    private String readPwdSp() {
        SharedPreferences sharedPreferences = getSharedPreferences(RegisterActivity.LOGIN_INFO, MODE_PRIVATE);
        String password = sharedPreferences.getString(mUserName, "");
        return password;
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context,ModifyPasswordActivity.class);
        context.startActivity(intent);
    }
}
