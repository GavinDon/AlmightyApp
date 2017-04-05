package com.ln.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by linan   on 2017/3/7.
 */

public abstract class BaseFragment extends Fragment {
    protected View self;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(this.setLayout(), container, false);

        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        ButterKnife.bind(this, this.self);
        this.initViews(this.self, savedInstanceState);
        return this.self;
    }

    public abstract int setLayout();

    public abstract void initViews(View self, Bundle savedInstanceState);


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
        Intent mIntent = new Intent(this.getActivity(), clz);
        if (bundle != null) mIntent.putExtras(bundle);
        startActivity(mIntent);
        if (isClose) {
            this.getActivity().finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
