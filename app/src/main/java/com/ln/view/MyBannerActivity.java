package com.ln.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ln.widgets.MyBanner;

import java.util.LinkedList;

public class MyBannerActivity extends AppCompatActivity {
    private MyBanner myBanner;
    private LinkedList<String> imgsUrl = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_banner);
        myBanner = (MyBanner) findViewById(R.id.banner);
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        myBanner.setVpData(imgsUrl);
        myBanner.getViewPager().setOnItemClickListener((vp, position) -> {
            Toast.makeText(this, position + "=", Toast.LENGTH_SHORT).show();
            Intent mIntent = new Intent(this, DatabindingActivity.class);
            startActivity(mIntent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myBanner.stop();
    }


}
