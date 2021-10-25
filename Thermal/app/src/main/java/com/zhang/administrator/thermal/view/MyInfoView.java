package com.zhang.administrator.thermal.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.zhang.administrator.thermal.R;

/**
 * Created by EWorld
 * 2021/10/25
 */
public class MyInfoView {
    private Activity activity;
    private LayoutInflater mInflater;
    private View mView;

    public MyInfoView(Activity activity) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        mView = mInflater.inflate(R.layout.view_layout_my_info, null);


        mView.setVisibility(View.VISIBLE);
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
