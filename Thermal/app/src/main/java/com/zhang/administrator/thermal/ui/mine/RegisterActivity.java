package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
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

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.Bmob;


/**
 * Created by Administrator on 2016/5/19.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    public static final String LOGIN_INFO = "login_info";
    public static String APPID = "612c964de8567f521833d1182ceb8c2e";
    private TextView mBack;
    private TextView mTitle;
    private Button mRegister;
    private EditText mNameEt, mPwdEt, mPwdAgainEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register_new);
        initView();
        initListener();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mRegister = (Button) findViewById(R.id.btn_register);
        mNameEt = (EditText) findViewById(R.id.et_username);
        mPwdEt = (EditText) findViewById(R.id.et_input_password);
        mPwdAgainEt = (EditText) findViewById(R.id.et_input_password_again);

        mTitle.setText("注册");

    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_back:
                finish();
                break;
            case R.id.btn_register:
                String userName = mNameEt.getText().toString().trim();
                String password = mPwdEt.getText().toString().trim();
                String passwordAgain = mPwdAgainEt.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(passwordAgain)) {
                    Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.equals(passwordAgain)) {
                    Toast.makeText(this, "两次输入的密码不一样！", Toast.LENGTH_SHORT).show();
                    return;
                } else if (isExistUserName(userName)) {
                    Toast.makeText(this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(userName, password);
                    //注册成功把用户名传递到LoginActivity中
                    Intent data = new Intent();
                    data.putExtra("user_name", userName);
                    setResult(RESULT_OK, data);
                    finish();
                }
                break;

        }
    }

    /**
     * 从SharedPreferences中读取用户名，并判断用户名是否存在
     *
     * @param userName
     * @return
     */
    private boolean isExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences preferences = getSharedPreferences(LOGIN_INFO, MODE_PRIVATE);
        String password = preferences.getString(userName, "");
        if (!TextUtils.isEmpty(password)) {
            hasUserName = true;
        }
        return hasUserName;
    }

    /**
     * 保存用户名和密码到SharedPreferences
     *
     * @param userName
     * @param password
     */
    private void saveRegisterInfo(String userName, String password) {
        String md5Pwd = MD5Utils.md5(password);//密码使用MD5加密
        SharedPreferences sharedPreferences = getSharedPreferences(LOGIN_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //用户名为key,密码为value保存
        editor.putString(userName, md5Pwd);
        editor.commit();
    }

    public static void startActivityForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }
}
