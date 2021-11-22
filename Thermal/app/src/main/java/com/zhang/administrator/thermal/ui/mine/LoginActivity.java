package com.zhang.administrator.thermal.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/4/25.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static String APPID = "612c964de8567f521833d1182ceb8c2e";
    private ImageButton mBack;
    private TextView mRegister;
    private EditText et_username,et_password;
    private Button btnLogin;
    private LinearLayout mHeadPhotoLayout;
    private TextView mChangePassWd;
    private RelativeLayout mRlLoginOut;
    private Button mBtnLoginOut;
    private View view;
    private static final boolean flag= true;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, APPID);
        initView();
        initListener();
    }



    private void initView() {
        mBack = (ImageButton) findViewById(R.id.login_back);
        mRegister = (TextView) findViewById(R.id.register);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
      //  mChangePassWd = (TextView) findViewById(R.id.tv_ChangePassWd);

//        LayoutInflater factory = LayoutInflater.from(Login_Activity.this);
//        final View textEntryView = factory.inflate(R.layout.layout_set, null);
//        RelativeLayout rlLoginOut = (RelativeLayout) textEntryView.findViewById(R.id.rl_login);

//        mRlLoginOut = (RelativeLayout) view.inflate(this, R.layout.layout_set, null);
//        mRlLoginOut = (RelativeLayout) view.findViewById(R.id.rl_login);
//        mBtnLoginOut = (Button) view.inflate(this, R.layout.layout_set, null);
//        mBtnLoginOut = (Button) view.findViewById(R.id.btnLoginOut);
    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        //mChangePassWd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();



                break;

//            case R.id.tv_ChangePassWd:
//                BmobUser.updateCurrentUserPassword(this, "旧密码", "新密码", new UpdateListener() {
//                    @Override
//                    public void onSuccess() {
//                        Toast.makeText(Login_Activity.this, "密码修改成功，可以用新密码进行登录", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//
//                        Toast.makeText(Login_Activity.this, "密码修改失败："+ s +"("+ i +")", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
        }

    }



//    private void testGetCurrentUser() {
//        BmobUser bmobUser = BmobUser.getCurrentUser(context);
//        if(bmobUser != null){
//            // 允许用户使用应用
//        }else{
//            //缓存用户对象为空时， 可打开用户注册界面…
//            Toast.makeText(Login_Activity.this, "你还没有注册，不妨先去注册吧！", Toast.LENGTH_LONG).show();
//        }
//    }


}
