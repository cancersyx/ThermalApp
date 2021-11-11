package com.zhang.administrator.thermal.ui.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.MainActivity;


/**
 * Created by Administrator on 2016/5/20.
 */
public class FirstVideoActivity extends Activity implements View.OnClickListener {

    private Button mPlay,mPause,mStop;
    private VideoView mVideo;
    private ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first_video);

        initView();
        Listener();
        mVideo.setVideoPath("/storage/emulated/0/video/stretch.3gp");
        mVideo.requestFocus();
    }

    private void initView() {
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button) findViewById(R.id.pause);
        mStop = (Button) findViewById(R.id.stop);
        mVideo = (VideoView) findViewById(R.id.first_video);
        mBack = (ImageView) findViewById(R.id.First_Video_back);

    }

    private void Listener() {
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.First_Video_back:
                startActivity(new Intent(FirstVideoActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.play:
                mVideo.start();
                break;
            case R.id.pause:
                if(mVideo.isPlaying()){
                    mVideo.pause();
                }else{
                    mVideo.start();
                }
                break;
            case R.id.stop:
                mVideo.stopPlayback();
                break;


        }
    }
}
