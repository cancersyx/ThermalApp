package com.zhang.administrator.thermal.ui.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;
import com.zsf.common.LUtils;

import java.io.IOException;

import androidx.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindDetailActivity extends BaseActivity {
    private static final String TAG = "FindDetailActivity";
    private TextView mBackTv;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mBackTv = findViewById(R.id.tv_title_back);
        mProgressBar = findViewById(R.id.loading_progressbar);
        mWebView = findViewById(R.id.web_view);

    }

    private void initEvent() {
        mBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        FindBean.DataDTO dataDTO = (FindBean.DataDTO) getIntent().getSerializableExtra("find_bean");
        String pageUrl = dataDTO.getPage_url();
        LUtils.d(TAG, ">>>>>>getTitle =  " + dataDTO.getTitle());
        LUtils.d(TAG, ">>>>>>getPage_url =  " + dataDTO.getPage_url());
        LUtils.d(TAG, ">>>>>>getKeywords = " + dataDTO.getKeywords());
        LUtils.d(TAG, ">>>>>>getDate = " + dataDTO.getDate());
        mWebView.loadUrl(pageUrl);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebSettings webSettings = mWebView.getSettings();
                webSettings.setBuiltInZoomControls(true);
                view.loadUrl(pageUrl);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
                if (newProgress >= 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        /*OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(dataDTO.getPage_url())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });*/

    }


    public static void startActivity(Context context, FindBean.DataDTO findBean) {
        Intent intent = new Intent(context, FindDetailActivity.class);
        intent.putExtra("find_bean", findBean);
        context.startActivity(intent);
    }
}
