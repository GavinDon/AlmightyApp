package com.ln.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity  {
   private  WebView mWebView;
    private String strUrl;
    private String strTitle;
    private WebSettings mWebSettings;
    private TextView tvTitle;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        tvTitle= (TextView) findViewById(R.id.news_title);
        mToolbar= (Toolbar) findViewById(R.id.news_toolbar);
        strUrl=getIntent().getStringExtra("url");
        strTitle=getIntent().getStringExtra("title");
        initWebView();
    }



    private void initWebView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!strTitle.isEmpty()){
            tvTitle.setText(strTitle);
        }
        mWebView= (WebView) findViewById(R.id.web);
        mWebSettings=  mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            // 设置自动加载图片
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        mWebSettings.getAllowFileAccess();
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.loadUrl(strUrl);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            this.finish();
        }
        return true;
    }
}
