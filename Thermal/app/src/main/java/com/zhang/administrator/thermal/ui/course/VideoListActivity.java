package com.zhang.administrator.thermal.ui.course;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zsf.common.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/11
 */
public class VideoListActivity extends BaseActivity {
    private TextView mBackTv;
    private TextView mTitleTv;
    private ImageView mChapterImg;
    private TextView mIntroTab;
    private TextView mVideoTab;
    private TextView mIntroContent;
    private ScrollView mIntroScroll;
    private RecyclerView mRecyclerView;

    private List<VideoBean> mVideoList;
    private VideoListAdapter mVideoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        initView();
        initData();
        initEvent();

    }

    private void initView() {
        mBackTv = findViewById(R.id.tv_title_back);
        mTitleTv = findViewById(R.id.tv_title);
        mChapterImg = findViewById(R.id.iv_chapter);
        mIntroTab = findViewById(R.id.tv_intro);
        mVideoTab = findViewById(R.id.tv_video);
        mIntroScroll = findViewById(R.id.sl_intro);
        mIntroContent = findViewById(R.id.tv_chapter_intro_content);
        mRecyclerView = findViewById(R.id.video_recycler);

        mVideoAdapter = new VideoListAdapter();
        mRecyclerView.setAdapter(mVideoAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVideoList = new ArrayList<>();
        mVideoAdapter.addItems(mVideoList);
    }

    private void initEvent() {
        mBackTv.setOnClickListener(v -> finish());
        mIntroTab.setOnClickListener(v -> {
            mRecyclerView.setVisibility(View.GONE);
            mIntroScroll.setVisibility(View.VISIBLE);
            mIntroTab.setBackgroundColor(Color.parseColor("#FD3A3A"));
            mVideoTab.setBackgroundColor(Color.parseColor("#FFFFFF"));
            mIntroTab.setTextColor(Color.parseColor("#FFFFFF"));
            mVideoTab.setTextColor(Color.parseColor("#000000"));
        });
        mVideoTab.setOnClickListener(v -> {
            mRecyclerView.setVisibility(View.VISIBLE);
            mIntroScroll.setVisibility(View.GONE);
            mIntroTab.setBackgroundColor(Color.parseColor("#FFFFFF"));
            mVideoTab.setBackgroundColor(Color.parseColor("#FD3A3A"));
            mIntroTab.setTextColor(Color.parseColor("#000000"));
            mVideoTab.setTextColor(Color.parseColor("#FFFFFF"));
        });
        mVideoAdapter.setItemClickListener(item -> VideoPlayActivity.startActivity(VideoListActivity.this, item.videoPath, item.secondTitle));
    }

    private void initData() {
        int chapterId = getIntent().getIntExtra("chapterId", 0);
        String chapterTitle = getIntent().getStringExtra("chapterTitle");
        String videoIntro = getIntent().getStringExtra("videoIntro");

        mTitleTv.setText(chapterTitle);
        mIntroContent.setText(videoIntro);


        try {
            InputStream is = getResources().getAssets().open("video/video_data.json");
            JSONArray jsonArray = new JSONArray(read(is));
            mVideoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                VideoBean bean = new VideoBean();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                if (jsonObj.getInt("chapterId") == chapterId) {
                    bean.chapterId = jsonObj.getInt("chapterId");
                    bean.videoId = Integer.parseInt(jsonObj.getString("videoId"));
                    bean.title = jsonObj.getString("title");
                    bean.secondTitle = jsonObj.getString("secondTitle");
                    bean.videoPath = jsonObj.getString("videoPath");
                    mVideoList.add(bean);
                }
                bean = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mVideoAdapter.addItems(mVideoList);
        }


    }

    private String read(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try {
            sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * @param context
     * @param chapterId  章ID
     * @param videoIntro 章简介
     */
    public static void startActivity(Context context, int chapterId, String chapterTitle, String videoIntro) {
        Intent intent = new Intent(context, VideoListActivity.class);
        intent.putExtra("chapterId", chapterId);
        intent.putExtra("chapterTitle", chapterTitle);
        intent.putExtra("videoIntro", videoIntro);
        context.startActivity(intent);
    }
}
