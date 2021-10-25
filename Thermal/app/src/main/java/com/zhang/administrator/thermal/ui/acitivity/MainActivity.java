package com.zhang.administrator.thermal.ui.acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.mine.SettingActivity;
import com.zhang.administrator.thermal.view.CourseView;
import com.zhang.administrator.thermal.view.MyInfoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mTitleContainer;
    private TextView mBackTv;
    private TextView mTitle;
    private Button mDiscoveryBtn, mCourseBtn, mExerciseBtn, mMineBtn;
    private TextView marquee;
    private FrameLayout mBodyContainer;

    private CourseView mCourseView;
    private MyInfoView mMyInfoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();

    }

    private void initData() {
        mTitleContainer.setBackgroundColor(Color.parseColor("#FF1592F2"));
        mTitle.setText("热处理");
        mBackTv.setVisibility(View.INVISIBLE);

    }


    private void initView() {
        mTitleContainer = findViewById(R.id.rl_title_container);
        mTitle = findViewById(R.id.tv_title);
        mBackTv = findViewById(R.id.tv_title_back);
        marquee = (TextView) findViewById(R.id.tv_marquee);
        mBodyContainer = findViewById(R.id.main_body);
        mCourseBtn = (Button) findViewById(R.id.btn_course);
        mExerciseBtn = (Button) findViewById(R.id.btn_exercise);
        mDiscoveryBtn = (Button) findViewById(R.id.btn_discovery);
        mMineBtn = (Button) findViewById(R.id.btn_mine);

    }

    private void initEvent() {
        mBackTv.setOnClickListener(this);
        mCourseBtn.setOnClickListener(this);
        mExerciseBtn.setOnClickListener(this);
        mDiscoveryBtn.setOnClickListener(this);
        mMineBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_back:
                finish();
                break;
            case R.id.btn_course:
                selectBottomTab(0);
                break;
            case R.id.btn_exercise:
                selectBottomTab(1);
                break;
            case R.id.btn_discovery:
                selectBottomTab(2);
                break;
            case R.id.btn_mine:
                selectBottomTab(3);
                break;
        }
    }

    private void selectBottomTab(int index){
        removeAllView();
        createBodyView(index);
    }

    private void createBodyView(int index) {
        switch (index){
            case 0:
                //课程界面
                if (mCourseView == null) {
                    mCourseView = new CourseView(this);
                    mBodyContainer.addView(mCourseView.getView());
                } else {
                    mCourseView.getView();
                }
                mCourseView.showView();
                break;
            case 1:
                //习题界面

                break;
            case 2:
                //发现界面

                break;
            case 3:
                //我的界面
                if (mMyInfoView == null) {
                    mMyInfoView = new MyInfoView(this);
                    mBodyContainer.addView(mMyInfoView.getView());
                } else {
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();
                break;
        }
    }

    private void removeAllView() {
        for (int i = 0; i < mBodyContainer.getChildCount(); i++) {
            mBodyContainer.getChildAt(i).setVisibility(View.GONE);
        }
    }
}
