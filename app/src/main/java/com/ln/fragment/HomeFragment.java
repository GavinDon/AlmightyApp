package com.ln.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;

import com.ln.base.BaseFragment;
import com.ln.view.R;
import com.ln.widgets.MyBanner;
import com.ln.widgets.TextAdapter;

import java.util.LinkedList;

import butterknife.Bind;

/**
 * Created by linan   on 2017/4/5.
 */

public class HomeFragment extends BaseFragment {
    LinkedList<String> imgsUrl; //轮播图片URL合集
    @Bind(R.id.banner)
    MyBanner mBanner;
    @Bind(R.id.avf)
    AdapterViewFlipper flipper;

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
        initBanner();
        initAdapterFlipper();
    }

    /**
     * adapterViewFlipper实现文字滚动
     */
    private void initAdapterFlipper() {
        String[] lst = getActivity().getResources().getStringArray(R.array.flipper);
        TextAdapter adapter = new TextAdapter(lst);
        flipper.setAdapter(adapter);
//        flipper.setFlipInterval(2000);
//        flipper.setInAnimation(this.getContext(),R.animator.flipper_in);
//        flipper.setOutAnimation(this.getContext(),R.animator.flipper_out);
        flipper.setAutoStart(true);
    }

    /**
     * 轮播
     */
    private void initBanner() {
        imgsUrl = new LinkedList<>();
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        //添加广告数据
        mBanner.setVpData(imgsUrl);
    }
}
