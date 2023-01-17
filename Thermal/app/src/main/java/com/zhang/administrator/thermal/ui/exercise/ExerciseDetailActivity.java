package com.zhang.administrator.thermal.ui.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zhang.administrator.thermal.util.AnalysisUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class ExerciseDetailActivity extends BaseActivity implements ExercisesDetailAdapter.OnClickOptionListener {
    private static final String TAG = "ExerciseDetailActivity";
    private TextView mBack;
    private TextView mTitle;
    private RecyclerView mRecyclerView;

    private ExercisesDetailAdapter mDetailAdapter;
    private List<ExerciseBean> mExerciseList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mRecyclerView = findViewById(R.id.question_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDetailAdapter = new ExercisesDetailAdapter(this);
        mRecyclerView.setAdapter(mDetailAdapter);

        mExerciseList = new ArrayList<>();


    }

    private void initEvent() {
        mBack.setOnClickListener(v -> finish());

    }

    private void initData() {
        int chapterId = getIntent().getIntExtra("chapter_id", 0);
        String chapterTitle = getIntent().getStringExtra("chapter_title");
        if (chapterTitle.length() >= 10) {
            mTitle.setTextSize(16);
        }
        mTitle.setText(chapterTitle);

        //从xml文件中获取习题数据
        try {
            InputStream is = getResources().getAssets().open("exercise_chapter_" + chapterId + ".xml");
            mExerciseList = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDetailAdapter.addItems(mExerciseList);
        }


    }


    public static void startActivity(Context context, int chapterId, String chapterTitle) {
        Intent intent = new Intent(context, ExerciseDetailActivity.class);
        intent.putExtra("chapter_id", chapterId);
        intent.putExtra("chapter_title", chapterTitle);
        context.startActivity(intent);
    }

    @Override
    public void onSelectA(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD) {

    }

    @Override
    public void onSelectB(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD) {

    }

    @Override
    public void onSelectC(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD) {

    }

    @Override
    public void onSelectD(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD) {

    }
}
