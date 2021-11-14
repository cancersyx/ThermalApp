package com.zhang.administrator.thermal.ui.find;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.exercise.ExerciseDetailActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindView {
    private static final String TAG = "FindView";
    private Activity mActivity;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private TextView mBackTv;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private List<FindBean> mFindBeanList;
    private FindAdapter mFindAdapter;

    public FindView(Activity activity) {
        this.mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.view_layout_find, null);
        mRecyclerView = mCurrentView.findViewById(R.id.find_recycler);
        mBackTv = mCurrentView.findViewById(R.id.tv_title_back);
        mTitle = mCurrentView.findViewById(R.id.tv_title);
        mBackTv.setVisibility(View.INVISIBLE);

        mFindAdapter = new FindAdapter(mActivity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mFindAdapter);
        initData();
    }


    private void initData() {
        mTitle.setText("发现");
        mFindBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FindBean findBean = new FindBean();
            findBean.thumb = R.drawable.img_chapter_0;
            findBean.title = "金属材料及热处理";
            findBean.topics = new String[]{"材料", "模具"};
            mFindBeanList.add(findBean);
        }

        mFindAdapter.addItems(mFindBeanList);

        mFindAdapter.setItemClickListener(item -> FindDetailActivity.startActivity(mActivity));
    }

    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
