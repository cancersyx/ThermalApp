package com.zhang.administrator.thermal.ui.mine;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhang.administrator.thermal.R;

/**
 * Created by EWorld
 * 2021/10/25
 */
public class MyInfoView {
    private Activity activity;
    private LayoutInflater mInflater;
    private View mView;

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

        mAbout = mView.findViewById(R.id.rl_mine_about);

        mView.setVisibility(View.VISIBLE);

        initEvent();
    }

    private void initEvent() {
        mAbout.setOnClickListener(v -> AboutProductActivity.startActivity(activity));
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
