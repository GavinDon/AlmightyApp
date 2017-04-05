package com.ln.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ln.base.BaseActivity;
import com.ln.fragment.HomeFragment;
import com.ln.fragment.NewsFragment;
import com.ln.fragment.PersonalFragment;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    @Bind(R.id.bottom_bar)
    BottomNavigationBar bottomNavigationBar;

    private HomeFragment homeF; //首页fragment
    private NewsFragment newsF; //新闻资讯fragment
    private PersonalFragment personalF; //个人中心fragment
    private List<Fragment> fragmentLst; //所有fragment的集合
    private FragmentManager fm;
    private String homeTag = HomeFragment.class.getSimpleName();
    private String newsTag = NewsFragment.class.getSimpleName();
    private String personalTag = PersonalFragment.class.getSimpleName();

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.news, "资讯").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.personal, "个人中心").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        fm = this.getSupportFragmentManager();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }


    /**
     * 设置显示homeFragment;
     */
    private void setDefaultFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragments(ft);
        homeF=new HomeFragment();  //需要先new 出来 要不然 会出现重叠
        ft.add(R.id.frame,homeF, homeTag);
        ft.commit();
    }

    /**
     * 隐藏掉所有的fragment
     * @param ft
     */
    private void hideFragments(FragmentTransaction ft) {
        if (homeF != null) {
            ft.hide(homeF);
        }
        if (newsF != null) {
            ft.hide(newsF);
        }
        if (personalF != null) {
            ft.hide(personalF);
        }
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragments(ft); //add- hide -show
        switch (position) {
            case 0:
                if (homeF == null) {
                    homeF = new HomeFragment();
                    ft.add(R.id.frame, homeF, homeTag);
                } else {
                    ft.show(homeF);
                }
                break;
            case 1:
                if (newsF == null) {
                    newsF = new NewsFragment();
                    ft.add(R.id.frame, newsF, newsTag);
                } else {
                    ft.show(newsF);
                }
                break;
            case 2:
                if (personalF == null) {
                    personalF = new PersonalFragment();
                    ft.add(R.id.frame, personalF, personalTag);
                } else {
                    ft.show(personalF);
                }
                break;
        }
        ft.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
    }
}
