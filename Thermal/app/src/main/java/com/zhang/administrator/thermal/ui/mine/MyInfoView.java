package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.util.AnalysisUtils;

/**
 * Created by EWorld
 * 2021/10/25
 */
public class MyInfoView {
    private Activity activity;
    private LayoutInflater mInflater;
    private View mView;
    private ImageView mAvatar;
    private TextView mUserNameTv;
    private RelativeLayout mPlayHistory;
    private RelativeLayout mFavorite;
    private RelativeLayout mSetting;
    private RelativeLayout mCleanCache;
    private RelativeLayout mAbout;

    public MyInfoView(Activity activity) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        mView = mInflater.inflate(R.layout.view_layout_my_info, null);
        mAvatar = mView.findViewById(R.id.iv_avatar);
        mUserNameTv = mView.findViewById(R.id.tv_name);
        mPlayHistory = mView.findViewById(R.id.rl_mine_history);
        mFavorite = mView.findViewById(R.id.rl_mine_collect);
        mSetting = mView.findViewById(R.id.rl_mine_setting);
        mCleanCache = mView.findViewById(R.id.rl_mine_clean_cache);
        mAbout = mView.findViewById(R.id.rl_mine_about);

        mView.setVisibility(View.VISIBLE);

        initEvent();
        setLoginParams(readLoginStatus());
    }

    private void initEvent() {
        mAvatar.setOnClickListener(v -> openPersonView());
        mUserNameTv.setOnClickListener(v -> openPersonView());
        mPlayHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()) {
                    // TODO: 2021/11/22  跳转到播放记录

                } else {
                    Toast.makeText(activity, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()) {
                    // TODO: 2021/11/22 跳转到收藏

                } else {
                    Toast.makeText(activity, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()) {
                    Intent intent = new Intent(activity, SettingActivity.class);
                    activity.startActivityForResult(intent, 100);
                } else {
                    Toast.makeText(activity, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCleanCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAbout.setOnClickListener(v -> AboutProductActivity.startActivity(activity));
    }

    private void openPersonView() {
        if (readLoginStatus()) {
            UserInfoActivity.startActivity(activity);
        } else {
            //跳转到登录界面
            Intent intent = new Intent(activity, LoginNewActivity.class);
            activity.startActivityForResult(intent, 100);
        }
    }

    private boolean readLoginStatus() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(RegisterActivity.LOGIN_INFO, Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("is_login", false);
        return isLogin;
    }

    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            mUserNameTv.setText(AnalysisUtils.readLoginUserName(activity));
        } else {
            mUserNameTv.setText("点击登录");
        }
    }

    public View getView() {
        if (mView == null) {
            createView();
        }
        return mView;
    }

    public void showView() {
        if (mView == null) {
            createView();
        }
        mView.setVisibility(View.VISIBLE);
    }


}
