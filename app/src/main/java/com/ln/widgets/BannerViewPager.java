package com.ln.widgets;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>
 * viewPager轮播
 * </p>
 * Created by linan   on 2017/3/28.
 */

public class BannerViewPager extends ViewPager {
    private Context mContext;
    private LinkedList<String> imgLst = new LinkedList<>(); //图片链接集合
    private boolean isTouch = false;
    private int currentPage;
    private Disposable mDisposable;
    private int interval = 3;


    public BannerViewPager(Context context) {
        super(context);
        this.mContext = context;
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void setData(LinkedList<String> imgUrls) {
        imgLst.clear();
        imgLst.addAll(imgUrls);
        imgLst.add(0, imgUrls.get(imgUrls.size() - 1)); //给第一个图片url前面加上集合中最后一个url
        imgLst.add(imgUrls.get(0)); //给最后一个加上第一个Url
        init();
    }


    private void init() {
        this.setAdapter(new BannerAdapter()); //设置viewPager适配器
        setCurrentItem(1); //设置当前页面索引为1,（即为前后加一个页面之后显示的第2个页面）
        start();
        this.addOnPageChangeListener(new myOnPageChangerListener()); //设置页面改变监听
        //当按下时让停止循环
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        stopInterval();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                        start();
                        break;
                }
                return false;
            }
        });
        setPageDuration(300);


    }

    /**
     * 设置平滑滚动时间d
     *
     * @param duration
     */
    public void setPageDuration(int duration) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(
                    this.getContext(), new AccelerateInterpolator());
            field.set(this, scroller);
            scroller.setmDuration(duration);// 设置图片平滑滚动持续的时间
        } catch (Exception e) {
        }

    }


    /**
     * 开始执行轮播
     */
    private void start() {
        mDisposable = Observable.interval(interval, interval, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (!isTouch) {
                            currentPage++;
                            setCurrentItem(currentPage);
                        }
                    }
                });
    }

    /**
     * 在取消轮播的时候应该取消订阅，否则引起OOM
     */
    public void stopInterval() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    /**
     * 页面改变监听器
     */
    class myOnPageChangerListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mOnSelectListener.onSelectListener(position);
        }

        /**
         * * @param state The new scroll state.
         *
         * @param state
         * @see ViewPager#SCROLL_STATE_IDLE      闲置中，什么都没做
         * @see ViewPager#SCROLL_STATE_DRAGGING  滑动中
         * @see ViewPager#SCROLL_STATE_SETTLING  表示滑动完毕
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case SCROLL_STATE_IDLE:
                    if (getCurrentItem() == 0) {
                        //如果当前的item为0 则显示 最后一个view ;
                        setCurrentItem(imgLst.size() - 2, false);
                    } else if (getCurrentItem() == imgLst.size() - 1) {
                        setCurrentItem(1, false);

                    }
                    currentPage = getCurrentItem();
                    isTouch = false;
                    break;
                case SCROLL_STATE_DRAGGING:
                    //滑动ViewPager的时候()
                    isTouch = true;
                    break;
            }
        }
    }

    /**
     * viewPager适配器
     */
    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgLst.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * @param container
         * @param position
         * @return object 就是创建view的key ,这个key  可以为任何对象 。只要能和view 一一对应上就OK
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView view = new ImageView(mContext);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            Glide.with(mContext).load(imgLst.get(position)).asBitmap().into(view);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            if (mOnItemClickListener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(BannerViewPager.this, position);
                    }
                });
//                if(mOnSelectListener!=null){
//                    mOnSelectListener.onSelectListener(position);
//                }
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    private OnItemClickListener mOnItemClickListener;
    private OnSelectedPointListener mOnSelectListener;

    public interface OnItemClickListener {
        void onItemClick(BannerViewPager vp, int position);
    }

    public interface OnSelectedPointListener {
        void onSelectListener(int position);
    }

    public void setOnSelectListener(OnSelectedPointListener listener) {
        this.mOnSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
