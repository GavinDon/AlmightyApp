package com.ln.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ln.view.R;

import butterknife.ButterKnife;

/**
 * Created by linan   on 2017/3/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(this.setLayout());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// 隐藏软键盘
        ButterKnife.bind(this);
        initToolbar();
        this.initView(savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int setLayout();

    /**
     * 初始化toolbar
     */
    protected void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        mTitle = (TextView) findViewById(R.id.app_txt);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 设置toolbar标题居中
     */
    public void setTitle(String title) {
        if (null != title) {
            mTitle.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
    public void gotoActivity(Class c){
        gotoActivity(c,false);
    }

    /**
     * 打开Activity并不需要传值
     */
    public void gotoActivity(Class<?> clz, boolean isClose) {
        gotoActivity(clz, isClose, null);

    }

    /**
     * 打开Activity并传值跳转成功关闭当前Activity
     *
     * @param clz     需要跳转到activity
     * @param isClose 是否需要关闭当前Activity
     * @param bundle  传递bundle数据
     */
    public void gotoActivity(Class<?> clz, boolean isClose, Bundle bundle) {
        Intent mIntent = new Intent(this, clz);
        if (bundle != null) mIntent.putExtras(bundle);
        startActivity(mIntent);
        if (isClose) {
            this.finish();
        }
    }
}
