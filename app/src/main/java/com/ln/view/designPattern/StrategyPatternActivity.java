package com.ln.view.designPattern;

import android.os.Bundle;

import com.ln.base.BaseActivity;
import com.ln.view.R;

public class StrategyPatternActivity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("策略模式");
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_strategy_pattern;
    }
}
