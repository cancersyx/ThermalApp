package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.util.AnalysisUtils;

import java.io.File;
import java.math.BigDecimal;

import androidx.appcompat.app.AlertDialog;

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

    private TextView mCacheTv;

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
        mCacheTv = mView.findViewById(R.id.tv_cache);
        mView.setVisibility(View.VISIBLE);

        initEvent();
        setLoginParams(readLoginStatus());
        setCacheSize();
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
                showCacheDialog();
            }
        });

        mAbout.setOnClickListener(v -> AboutProductActivity.startActivity(activity));
    }

    private String getTotalCacheSize() {
        long cacheSize = getFolderSize(activity.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(activity.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    private long getFolderSize(File file) {
        long size = 0;
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getFolderSize(files[i]);
            } else {
                size = size + files[i].length();
            }
        }
        return size;
    }

    private String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result_1 = new BigDecimal(Double.toString(kiloByte));
            return result_1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result_2 = new BigDecimal(Double.toString(megaByte));
            return result_2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraByte = gigaByte / 1024;
        if (teraByte < 1) {
            BigDecimal result_3 = new BigDecimal(Double.toString(gigaByte));
            return result_3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }

        BigDecimal result_4 = new BigDecimal(Double.toString(teraByte));
        return result_4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    private void showCacheDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提醒！");
        builder.setMessage("是否清空缓存");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllCacheData();
                mCacheTv.setText("0KB");

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void clearAllCacheData() {
        deleteDir(activity.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(activity.getExternalCacheDir());
        }
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
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

    public void setCacheSize() {
        mCacheTv.setText(getTotalCacheSize());
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
