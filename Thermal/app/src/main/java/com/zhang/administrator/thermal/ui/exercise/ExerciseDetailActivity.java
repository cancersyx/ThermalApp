package com.zhang.administrator.thermal.ui.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class ExerciseDetailActivity extends BaseActivity {
    private TextView mBack;
    private TextView mTitle;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        initView();
        initEvent();
        initData();
    }

    private void initEvent() {
        mBack.setOnClickListener(v -> finish());
    }

    private void initData() {
        int chapterId = getIntent().getIntExtra("chapter_id",0);
        String chapterTitle = getIntent().getStringExtra("chapter_title");
        mTitle.setText(chapterTitle);
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitle = findViewById(R.id.tv_title);
        mRecyclerView = findViewById(R.id.question_recycler_view);


    }

    public static void startActivity(Context context, int chapterId, String chapterTitle) {
        Intent intent = new Intent(context, ExerciseDetailActivity.class);
        intent.putExtra("chapter_id", chapterId);
        intent.putExtra("chapter_title", chapterTitle);
        context.startActivity(intent);
    }
}
