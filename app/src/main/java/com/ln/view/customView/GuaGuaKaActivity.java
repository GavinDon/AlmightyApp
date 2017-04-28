package com.ln.view.customView;

import android.os.Bundle;

import com.ln.base.BaseActivity;
import com.ln.view.R;

public class GuaGuaKaActivity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("刮刮卡");
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_gua_gua_ka;
    }
}
