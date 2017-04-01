package com.ln.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ln.utils.MyTools;
import com.ln.view.R;

import java.util.LinkedList;

/**
 * <p>加入viewpager和指示器的控件
 * 支持直接在xml 里配置
 * </p>
 * Created by linan   on 2017/3/28.
 */

public class MyBanner extends FrameLayout implements BannerViewPager.OnSelectedPointListener {
    public boolean showIndicate; //显示指示器 (默认显示)
    public boolean showTextTip; //是否显示提示文字(默认不显示)
    private int vpWidth = LayoutParams.MATCH_PARENT; //viewPager宽度
    private int vpheight = 180; //viewPager高度
    private int tipColor; //tip文字颜色
    private int tipSize;   //提示文字颜色
    private int indicatePosition;
    private BannerViewPager mViewPager;
    private LinkedList<String> lst;

    private Context context;

    public MyBanner(Context context) {
        this(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyBanner);
        if (ta != null) {
            showIndicate = ta.getBoolean(R.styleable.MyBanner_showCircle, true);
            showTextTip = ta.getBoolean(R.styleable.MyBanner_showTipText, false);
            vpheight = ta.getDimensionPixelSize(R.styleable.MyBanner_vpHeight, MyTools.dp2px(context, vpheight));
            vpWidth = ta.getDimensionPixelSize(R.styleable.MyBanner_vpHeight, MyTools.dp2px(context, vpWidth));
            tipColor = ta.getColor(R.styleable.MyBanner_tipTxtColor, Color.WHITE); //默认字体 为白色
            tipSize = ta.getDimensionPixelSize(R.styleable.MyBanner_tipTxtSize, MyTools.sp2px(context, 16));
            indicatePosition = ta.getInt(R.styleable.MyBanner_indicatePosition, 1); //默认指示器显示在中间
            ta.recycle();
            createContainer();
        }
    }

    /**
     * setVpData 来为viewPager设置值
     *
     * @param lst
     */
    public void setVpData(LinkedList<String> lst) {
        this.lst = lst;
        mViewPager = new BannerViewPager(context);
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(matchParent, MyTools.dp2px(context, 180)));
        mViewPager.setData(lst);
        containerFl.addView(mViewPager);
        addIndicate();
        addView(containerFl);
    }

    private int matchParent = LayoutParams.MATCH_PARENT;
    private int wrapContent = LayoutParams.WRAP_CONTENT;
    private int indicateBackGround = R.drawable.selector_banner_point; //默认指示器的选择器
    private FrameLayout containerFl; //容器FrameLayout
    private LinearLayout indicateLayout; //指示器线性布局

    /**
     * 添加指示器
     */
    private void addIndicate() {
        indicateLayout = new LinearLayout(context);
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        ivParams.setMargins(5, 5, 5, 5);
        ImageView indicateIv; //指示器圆点
        for (int i = 0; i < lst.size() + 2; i++) {
            indicateIv = new ImageView(context);
            indicateIv.setBackgroundResource(indicateBackGround);
            indicateLayout.addView(indicateIv, ivParams);
        }
        hideAllIndicate();
        //若要改变指示器显示位置 修改gravity即可
        containerFl.addView(indicateLayout, new LayoutParams(wrapContent, wrapContent, Gravity.BOTTOM | Gravity.CENTER));
        mViewPager.setOnSelectListener(this);
    }

    /**
     * 因为在viewpager中onPageSelected()方法中回调过来的position
     * 所以先要设置全部为未选中状态
     * 再设置默认选这中第一个
     */
    private void hideAllIndicate() {
        for (int i = 0; i < indicateLayout.getChildCount(); i++) {
            indicateLayout.getChildAt(i).setEnabled(false);
        }
        indicateLayout.getChildAt(0).setVisibility(View.GONE);
        indicateLayout.getChildAt(lst.size() + 1).setVisibility(View.GONE);
        indicateLayout.getChildAt(1).setEnabled(true); //设置显示第一个指示器
    }


    /**
     * 创建一个banner的大容器
     */
    private void createContainer() {
        containerFl = new FrameLayout(context);

    }

    public void stop() {
        mViewPager.stopInterval();
    }

    public BannerViewPager getViewPager() {
        return mViewPager;
    }

    @Override
    public void onSelectListener(int position) {
        for (int i = 0; i < indicateLayout.getChildCount(); i++) {
            indicateLayout.getChildAt(i).setEnabled(false);
        }
//        if (position == 0) {
//            indicateLayout.getChildAt(lst.size() - 1).setEnabled(true);
//        } else if (position == lst.size() + 1) {
//            //由于在头尾各家一个。所以在原始的集合中需要加2.position变成+1 (lst.size+2-1)
//            indicateLayout.getChildAt(1).setEnabled(true);
//        }
        indicateLayout.getChildAt(position).setEnabled(true);
    }
}
