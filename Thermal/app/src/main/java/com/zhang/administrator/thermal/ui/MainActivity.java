package com.zhang.administrator.thermal.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.course.CourseView;
import com.zhang.administrator.thermal.ui.exercise.BookSectionExerciseView;
import com.zhang.administrator.thermal.ui.find.FindView;
import com.zhang.administrator.thermal.ui.mine.MyInfoView;
import com.zsf.common.LUtils;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RelativeLayout mTitleContainer;
    private TextView mBackTv;
    private TextView mTitle;
    private TextView mCourseTv, mExerciseTv, mDiscoveryTv, mMineTv;
    private TextView marquee;
    private FrameLayout mBodyContainer;

    private CourseView mCourseView;
    private BookSectionExerciseView mBookSectionExerciseView;
    private FindView mFindView;
    private MyInfoView mMyInfoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
        selectBottomTab(0);
        setBottomTabStatus(0);
    }


    private void initView() {
        mTitleContainer = findViewById(R.id.rl_title_container);
        mTitle = findViewById(R.id.tv_title);
        mBackTv = findViewById(R.id.tv_title_back);
        marquee = findViewById(R.id.tv_marquee);
        mBodyContainer = findViewById(R.id.main_body);
        mCourseTv = findViewById(R.id.btn_course);
        mExerciseTv = findViewById(R.id.btn_exercise);
        mDiscoveryTv = findViewById(R.id.btn_discovery);
        mMineTv = findViewById(R.id.btn_mine);

    }

    private void initData() {
        mTitleContainer.setBackgroundColor(Color.parseColor("#FF1592F2"));
        mTitle.setText("热处理");
        mBackTv.setVisibility(View.INVISIBLE);

    }

    private void initEvent() {
        mBackTv.setOnClickListener(this);
        mCourseTv.setOnClickListener(this);
        mExerciseTv.setOnClickListener(this);
        mDiscoveryTv.setOnClickListener(this);
        mMineTv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_back:
                finish();
                break;
            case R.id.btn_course:
                clearBottomTabStatus();
                selectBottomTab(0);
                setBottomTabStatus(0);
                break;
            case R.id.btn_exercise:
                clearBottomTabStatus();
                selectBottomTab(1);
                setBottomTabStatus(1);
                break;
            case R.id.btn_discovery:
                clearBottomTabStatus();
                selectBottomTab(2);
                setBottomTabStatus(2);
                break;
            case R.id.btn_mine:
                clearBottomTabStatus();
                selectBottomTab(3);
                setBottomTabStatus(3);
                break;
        }
    }

    private void setBottomTabStatus(int index){
        switch (index){
            case 0:
                mCourseTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                //mCourseTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                break;
            case 1:
                mExerciseTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                //mExerciseTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                break;
            case 2:
                mDiscoveryTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                //mDiscoveryTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                break;
            case 3:
                mMineTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                //mMineTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_txt_select));
                break;
        }
    }

    private void clearBottomTabStatus() {
        mCourseTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_normal));
        mExerciseTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_normal));
        mDiscoveryTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_normal));
        mMineTv.setTextColor(getResources().getColor(R.color.color_bottom_tab_txt_normal));
        //mCourseTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_normal));
        //mExerciseTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_normal));
        //mDiscoveryTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_normal));
        //mMineTv.setBackgroundColor(getResources().getColor(R.color.color_bottom_tab_normal));
    }

    private void selectBottomTab(int index) {
        removeAllView();
        createBodyView(index);
    }

    private void createBodyView(int index) {
        switch (index) {
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
                if (mBookSectionExerciseView == null){
                    mBookSectionExerciseView = new BookSectionExerciseView(this);
                    mBodyContainer.addView(mBookSectionExerciseView.getView());
                }else {
                    mBookSectionExerciseView.getView();
                }
                mBookSectionExerciseView.showView();
                break;
            case 2:
                //发现界面
                if (mFindView == null){
                    mFindView = new FindView(this);
                    mBodyContainer.addView(mFindView.getView());
                }else {
                    mFindView.getView();
                }
                mFindView.showView();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            //从设置界面或登录界面传递过来的登录状态
            boolean loginStatus = data.getBooleanExtra("is_login",false);
            LUtils.d(TAG,">>>>>> loginStatus = " + loginStatus);
            if (loginStatus){
                //登录成功
                // TODO: 2021/11/22
            }

            if (mMyInfoView != null){
                mMyInfoView.setLoginParams(loginStatus);
            }
        }
    }
}
