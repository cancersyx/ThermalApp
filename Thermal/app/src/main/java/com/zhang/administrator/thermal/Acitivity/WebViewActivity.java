package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/5/19.
 */
public class WebViewActivity extends Activity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl("http://baidu.com");
    }
}
