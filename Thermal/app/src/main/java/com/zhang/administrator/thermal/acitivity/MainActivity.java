package com.zhang.administrator.thermal.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button discovery,set;
    private ImageButton imagebut1,imagebut2,imagebut3,imagebut4;
    private TextView tv;
    private WebView mWebView;
    private LinearLayout mFirstVideo,mSecondVideo;
    private boolean flag = false;
    //private TextView mLogin;

    View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }



    private void initView() {
        discovery= (Button) findViewById(R.id.discovery);
        set= (Button) findViewById(R.id.set);
        imagebut1= (ImageButton) findViewById(R.id.imagebut1);
        imagebut2= (ImageButton) findViewById(R.id.imagebut2);
        imagebut3= (ImageButton) findViewById(R.id.imagebut3);
        imagebut4= (ImageButton) findViewById(R.id.imagebut4);
//        mWebView = (WebView) mView.inflate(this, R.layout.layout_webview, null);
//        mWebView= (WebView) findViewById(R.id.web_view);

        tv= (TextView) findViewById(R.id.tv);
        mFirstVideo = (LinearLayout) findViewById(R.id.ll_first_video);
        mSecondVideo = (LinearLayout) findViewById(R.id.ll_second_video);
        //mLogin = (TextView) findViewById(R.id.tv_login);

    }
    private void initData() {
        discovery.setOnClickListener(this);
        set.setOnClickListener(this);
        imagebut1.setOnClickListener(this);
        imagebut2.setOnClickListener(this);
        imagebut3.setOnClickListener(this);
        imagebut4.setOnClickListener(this);
        mFirstVideo.setOnClickListener(this);
        mSecondVideo.setOnClickListener(this);
       // mLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discovery:
//                Uri uri = Uri.parse("http://www.baidu.com/");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//                String url="http://www.baidu.com/";
//                mWebView.loadUrl(url);
                startActivity(new Intent(MainActivity.this,WebViewActivity.class));
                break;
            case R.id.set:
                flag = true;
                startActivity(new Intent(MainActivity.this, SetActivity.class));
                Intent intent=getIntent();
                finish();
                break;
            case R.id.imagebut1:
                startActivity(new Intent(MainActivity.this, MetalCategoryActivity.class));
                finish();
                break;
            case R.id.imagebut2:
                startActivity(new Intent(MainActivity.this, LiXueXingNengActivity.class));
                finish();
                break;

            case R.id.imagebut3:
                startActivity(new Intent(MainActivity.this, JinXiangZhuZhiActivity.class));
                finish();
                break;
            case R.id.imagebut4:
                startActivity(new Intent(MainActivity.this, FormulaActivity.class));
                finish();
                break;
            case R.id.ll_first_video:
                startActivity(new Intent(MainActivity.this,FirstVideoActivity.class));
                finish();
                break;
//            case R.id.tv_login:
//                startActivity(new Intent(MainActivity.this,Login_Activity.class));
//                finish();
//                break;

        }
    }
}
