package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.entity.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2016/5/19.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    public static String APPID = "612c964de8567f521833d1182ceb8c2e";
    private Button mRegister;
    private ImageButton mBack;
    private EditText et_username,et_password;
    private EditText et_phooneNo,et_againPassWd;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registerf);
        Bmob.initialize(this, APPID);
        initView();
        initListener();
    }

    private void initView() {
        mBack = (ImageButton) findViewById(R.id.register_back);
        mRegister = (Button) findViewById(R.id.btn_register);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_phooneNo = (EditText) findViewById(R.id.et_inputPhoneNo);
        et_againPassWd = (EditText) findViewById(R.id.et_againPassWd);

    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back:
                startActivity(new Intent(RegisterActivity.this,Login_Activity.class));
                break;

            case R.id.btn_register:
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String phoneNo = et_phooneNo.getText().toString();
                String againPassWd = et_againPassWd.getText().toString();

                final MyUser user = new MyUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setMobilePhoneNumber(phoneNo);
                user.setNickName("稻草人");
                user.setAge(23);
                user.setAddress("上海市");
                user.signUp(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                   //     BmobUser user = BmobUser.getCurrentUser(context);
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this,Login_Activity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                    }
                });

                break;

        }
    }


}
