package com.ln.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * description:tabLayout 适配器
 * Created by liNan on 2017/4/11 14:30
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private static final String[] title = {"头条", "社会", "国内","国际", "娱乐","体育","军事","科技","财经","时尚"};
    List<Fragment> fragmentLst;

    public TabLayoutAdapter(FragmentManager fm, List<Fragment> fragmentLst) {
        super(fm);
       this.fragmentLst= fragmentLst;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLst.get(position) ;
    }

    @Override
    public int getCount() {
        return fragmentLst.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
