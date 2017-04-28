package com.ln.view.customView;

import android.os.Bundle;

import com.ln.base.BaseActivity;
import com.ln.view.R;

public class BasicViewActivity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("基础view");
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_basic_view;
    }
}
