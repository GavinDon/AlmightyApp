package com.ln.view.customView;

import android.os.Bundle;
import android.view.View;

import com.ln.base.BaseActivity;
import com.ln.view.R;

import butterknife.OnClick;

public class MyViewCollection extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
            setTitle("自定义View");
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_my_view_collection;
    }

    @OnClick({R.id.basic_view, R.id.guaguaka,R.id.progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.basic_view:
                gotoActivity(BasicViewActivity.class);
                break;
            case R.id.guaguaka:
                gotoActivity(GuaGuaKaActivity.class);
                break;
            case R.id.progress:
                gotoActivity(ProgressDialogActivity.class);
                break;
        }
    }


}
