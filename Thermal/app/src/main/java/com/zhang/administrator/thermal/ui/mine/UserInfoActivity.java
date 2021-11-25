package com.zhang.administrator.thermal.ui.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.bean.MyUser;
import com.zhang.administrator.thermal.db.DBUtils;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zhang.administrator.thermal.util.AnalysisUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by EWorld
 * 2021/11/24
 * 个人中心
 */
public class UserInfoActivity extends BaseActivity {
    private TextView mBack, mTitle;
    private RelativeLayout mAvatarLayout;
    private ImageView mAvatarIv;
    private RelativeLayout mUserNameLayout;
    private TextView mUserNameTv;
    private RelativeLayout mNickNameLayout;
    private TextView mNickNameTv;
    private RelativeLayout mSexLayout;
    private TextView mSexTv;
    private RelativeLayout mSignatureLayout;
    private TextView mSignatureTv;

    private String mUserNameSp;
    private static final int CHANGE_NICKNAME = 1;
    private static final int CHANGE_SIGNATURE = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mUserNameSp = AnalysisUtils.readLoginUserName(this);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mAvatarLayout = findViewById(R.id.rl_avatar);
        mUserNameLayout = findViewById(R.id.rl_user_name);
        mNickNameLayout = findViewById(R.id.rl_nick_name);
        mSexLayout = findViewById(R.id.rl_sex);
        mSignatureLayout = findViewById(R.id.rl_signature);
        mAvatarIv = findViewById(R.id.iv_avatar_thumbnail);
        mUserNameTv = findViewById(R.id.tv_user_name);
        mNickNameTv = findViewById(R.id.tv_nick_name);
        mSexTv = findViewById(R.id.tv_sex);
        mSignatureTv = findViewById(R.id.tv_signature);

        mTitle.setText("个人资料");
    }

    private void initEvent() {
        mBack.setOnClickListener(v -> finish());
        mAvatarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021/11/24  设置头像
            }
        });
        mNickNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String nick = mNickNameTv.getText().toString();
               Bundle bundle = new Bundle();
               bundle.putString("content",nick);
               bundle.putString("title","昵称");
               bundle.putInt("flag",CHANGE_NICKNAME);
               enterActivityForResult(ChangeUserInfoActivity.class,101,bundle);
            }
        });
        mSexLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexDialog(mSexTv.getText().toString());
            }
        });
        mSignatureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signature = mSignatureTv.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("content",signature);
                bundle.putString("title","签名");
                bundle.putInt("flag",CHANGE_SIGNATURE);
                enterActivityForResult(ChangeUserInfoActivity.class,102,bundle);
            }
        });


    }

    private void initData() {
        MyUser myUser = null;
        myUser = DBUtils.getInstance(this).getUserInfo(mUserNameSp);
        if (myUser == null) {
            //数据库没有数据
            myUser = new MyUser();
            myUser.setUserName(mUserNameSp);
            myUser.setNickName("设置一个可爱的昵称^_^");
            myUser.setSex("男");
            myUser.setSignature("神秘的朋友，不想写点什么吗？");
            myUser.setAddress("北京");
            DBUtils.getInstance(this).saveUserInfo(myUser);
        }
        setUserInfo(myUser);
    }


    private void setUserInfo(MyUser myUser) {
        mUserNameTv.setText(mUserNameSp);
        mNickNameTv.setText(myUser.getNickName());
        mSexTv.setText(myUser.getSex());
        mSignatureTv.setText(myUser.getSignature());
    }

    private void showSexDialog(String sex) {
        int sexFlag = 0;
        if ("男".equals(sex)) {
            sexFlag = 0;
        } else if ("女".equals(sex)) {
            sexFlag = 1;
        }
        String items[] = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                setSexValue(items[which]);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    private void setSexValue(String sex) {
        mSexTv.setText(sex);
        //更新数据库数据
        DBUtils.getInstance(this).updateUserInfo("sex", sex, mUserNameSp);
    }

    private void enterActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 101:
                if (data != null){
                    String newInfo = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(newInfo)){
                        return;
                    }
                    mNickNameTv.setText(newInfo);
                    //更新数据库昵称
                    DBUtils.getInstance(this).updateUserInfo("nickName",newInfo,mUserNameSp);
                }
                break;
            case 102:
                if (data != null){
                    String newInfo = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(newInfo)){
                        return;
                    }
                    mSignatureTv.setText(newInfo);
                    //更新数据库昵称
                    DBUtils.getInstance(this).updateUserInfo("signature",newInfo, mUserNameSp);
                }
                break;
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }
}
