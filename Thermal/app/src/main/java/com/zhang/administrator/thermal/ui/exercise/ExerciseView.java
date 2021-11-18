package com.zhang.administrator.thermal.ui.exercise;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class ExerciseView {
    private static final String TAG = "ExerciseView";
    private Activity mActivity;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private TextView mBackTv;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private List<ExerciseBean> mExerciseList;
    private ExerciseAdapter mExerciseAdapter;

    public ExerciseView(Activity activity) {
        this.mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.view_layout_exercise, null);
        mRecyclerView = mCurrentView.findViewById(R.id.exercise_recycler);
        mBackTv = mCurrentView.findViewById(R.id.tv_title_back);
        mTitle = mCurrentView.findViewById(R.id.tv_title);
        mBackTv.setVisibility(View.INVISIBLE);

        mExerciseAdapter = new ExerciseAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mExerciseAdapter);
        initData();
    }


    private void initData() {
        mTitle.setText("习题");
        mExerciseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExerciseBean bean = new ExerciseBean();
            bean.id = (i + 1);
            switch (i) {
                case 0:
                    bean.title = "第1章 材料的性能";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg;
                    break;
                case 1:
                    bean.title = "第2章 材料的结构";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg_2;
                    break;
                case 2:
                    bean.title = "第3章 材料的凝固与相图";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg_3;
                    break;
                case 3:
                    bean.title = "第4章 金属的塑形变形与再结晶";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg_4;
                    break;
                case 4:
                    bean.title = "第5章 钢的热处理";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg;
                    break;
                case 5:
                    bean.title = "第6章 工业用钢";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg_2;
                    break;
                case 6:
                    bean.title = "第7章 服务";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg_3;
                    break;
                case 7:
                    bean.title = "第8章 铸铁";
                    bean.questionAmount = "共计5题";
                    bean.background = R.drawable.img_serial_number_bg_4;
                    break;
               /* case 8:
                    bean.title = "第9章 ";
                    bean.content = "";
                    bean.background = R.drawable.img_serial_number_bg;
                    break;
                case 9:
                    bean.title = "第10章 ";
                    bean.content = "";
                    bean.background = R.drawable.img_serial_number_bg_2;
                    break;*/
            }
            mExerciseList.add(bean);
        }
        mExerciseAdapter.addItems(mExerciseList);

        mExerciseAdapter.setItemClickListener(item -> ExerciseDetailActivity.startActivity(mActivity, item.id, item.title));
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
