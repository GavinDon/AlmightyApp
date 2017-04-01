package com.ln.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.ln.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        CollapsingToolbarLayout tl= (CollapsingToolbarLayout) findViewById(R.id.toolbarlayout);
        tl.setTitle("this is title");
        Intent i=new Intent(this,MyBannerActivity.class);
        startActivity(i);

    }

}
