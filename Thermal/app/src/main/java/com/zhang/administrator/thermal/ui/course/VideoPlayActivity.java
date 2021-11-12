package com.zhang.administrator.thermal.ui.course;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zhang.administrator.thermal.util.LUtils;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/11/12
 */
public class VideoPlayActivity extends BaseActivity {
    private static final String TAG = "VideoPlayActivity";
    private TextView mBackTv;
    private TextView mTitleTv;
    private VideoView mVideoView;
    private LinearLayout mPlayContainer;
    private ImageView mPlayIv;
    private SeekBar mSeekBar;
    private TextView mPlayDuration;
    private ImageView mFullscreenIv;
    private MediaController mController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mBackTv = findViewById(R.id.tv_title_back);
        mTitleTv = findViewById(R.id.tv_title);
        mVideoView = findViewById(R.id.video_play);
        mPlayContainer = findViewById(R.id.ll_video_control_container);
        mPlayIv = findViewById(R.id.iv_play_pause);
        mSeekBar = findViewById(R.id.video_seek_Bar);
        mPlayDuration = findViewById(R.id.tv_play_duration);
        mFullscreenIv = findViewById(R.id.iv_full_screen);

        mPlayIv.setImageResource(R.drawable.icon_video_play);
        mController = new MediaController(this);

    }

    private void initData() {
        String videoUrl = getIntent().getStringExtra("video_path");
        LUtils.d(TAG, ">>>>>> videoUrl = " + videoUrl);
        mSeekBar.setProgress(0);
        mPlayDuration.setText("00/00");
        mVideoView.setKeepScreenOn(true);//防止屏幕变暗
        mVideoView.setVideoURI(Uri.parse(videoUrl));
        mVideoView.setMediaController(mController);
        mController.setMediaPlayer(mVideoView);
    }

    private void initEvent() {
        mBackTv.setOnClickListener(v -> finish());
        mPlayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoView.isPlaying()) {
                    mPlayIv.setImageResource(R.drawable.icon_video_play);
                    mVideoView.pause();
                } else {
                    mPlayIv.setImageResource(R.drawable.icon_video_pause);
                    mVideoView.start();
                }
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public static void startActivity(Context context, String videoPath) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra("video_path", videoPath);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        }
    }
}
