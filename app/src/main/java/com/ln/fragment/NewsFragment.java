package com.ln.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ln.adapters.TabLayoutAdapter;
import com.ln.base.BaseFragment;
import com.ln.fragment.news.GuoJIFragment;
import com.ln.view.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by linan   on 2017/4/5.
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.news_vp)
    ViewPager mViewPager;

    private Context mContext;
    private List<Fragment> fragmentLst;

    @Override
    public int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
        mContext = self.getContext();
        addItems();
    }

    /**
     * tabLayout添加子项目
     */
    private void addItems() {
        fragmentLst = new ArrayList<>();
        fragmentLst.add(GuoJIFragment.newInstance("toutiao", null));
        fragmentLst.add(GuoJIFragment.newInstance("shehui", null));
        fragmentLst.add(GuoJIFragment.newInstance("guonei", null));
        fragmentLst.add(GuoJIFragment.newInstance("guoji", null));
        fragmentLst.add(GuoJIFragment.newInstance("yule", null));
        fragmentLst.add(GuoJIFragment.newInstance("tiyu", null));
        fragmentLst.add(GuoJIFragment.newInstance("junshi", null));
        fragmentLst.add(GuoJIFragment.newInstance("keji", null));
        fragmentLst.add(GuoJIFragment.newInstance("caijing", null));
        fragmentLst.add(GuoJIFragment.newInstance("shishang", null));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new TabLayoutAdapter(this.getChildFragmentManager(), fragmentLst));
        mViewPager.setCurrentItem(0);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


}
