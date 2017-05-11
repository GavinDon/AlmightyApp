package com.ln.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.utils.Log;

import static android.os.Build.VERSION_CODES.KITKAT;
import static com.ln.view.R.id.share;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private Toolbar mToolbar;
    private AppCompatButton shareButton;

    private WebView mWebView;
    private WebSettings mWebSettings;

    // 进度条
    private ProgressBar pbarMember = null;

    private String strTitle = "";
    private String strUrl = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        tvTitle = (TextView) findViewById(R.id.news_title);
        mToolbar = (Toolbar) findViewById(R.id.news_toolbar);
        pbarMember = (ProgressBar) findViewById(R.id.rbar_webview);
        shareButton = (AppCompatButton) findViewById(share);
        shareButton.setOnClickListener(this);
        strUrl = getIntent().getStringExtra("url");
        strTitle = getIntent().getStringExtra("title");
        initWeb();
    }

    /**
     * 初始化WebView
     */
    private void initWeb() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (!strTitle.isEmpty()) {
            tvTitle.setText(strTitle);
        }
        mWebView = (WebView) findViewById(R.id.web);
        mWebSettings = mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= KITKAT) {
            //设置自动加载图片
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        mWebView.getSettings().setSupportMultipleWindows(false);
        mWebView.getSettings().getAllowFileAccess();
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.loadUrl(strUrl);
        mWebView.setWebChromeClient(new WebChromeClienter());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!mWebView.getSettings().getLoadsImagesAutomatically()) {
                    mWebView.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == share) {
            goShare();
        }
    }

    class WebChromeClienter extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if (pbarMember.getProgress() < newProgress) {
                pbarMember.setProgress(newProgress);
                pbarMember.postInvalidate();
            }

            if (newProgress == 100) {
                pbarMember.setVisibility(View.GONE);
            }

            super.onProgressChanged(view, newProgress);

        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            this.finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 打开分享面板
     */
    private void goShare() {
        final UMWeb web = new UMWeb(strUrl); //分享链接
        web.setThumb(new UMImage(this, R.mipmap.icon));
        web.setTitle(strTitle);//标题
        new ShareAction(WebActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .withText("")
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
        ShareAction shareAction = new ShareAction(this);

        //自定义分享面板
        ShareBoardConfig config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
        config.setCancelButtonVisibility(true);
        config.setTitleVisibility(false);
        config.setIndicatorVisibility(false);
        shareAction.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.ALIPAY, SHARE_MEDIA.SINA);
        shareAction.open(config);

        shareAction.setShareboardclickCallback((snsPlatform, share_media) -> {
            if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                new ShareAction(WebActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withText("李南的纯金Iphone8 ")
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
            } else {
                Toast.makeText(WebActivity.this, "功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 分享回调
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(WebActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(WebActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(WebActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
