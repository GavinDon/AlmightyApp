package com.ln.view.customView;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ln.utils.MyTools;
import com.ln.view.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProgressDialogActivity extends AppCompatActivity {

    @BindView(R.id.circle_progress_dialog)
    CircleProgressDialog circleProgressDialog;
    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.mLayout)
    LinearLayout mRootView;

    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        ButterKnife.bind(this);
        Log.i("progress", "onCreate");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("progress", "onAttachedToWindow");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            init();
            circleProgressDialog.update();

        }

    }

    private void init() {
        width = mRootView.getWidth();
        height = mRootView.getHeight();
        PointF pointF1 = new PointF(0, MyTools.dp2px(this, 51));
        PointF pointF2 = new PointF(width - mImageView.getWidth(), height - mImageView.getHeight());
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setObjectValues(pointF1, pointF2);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setEvaluator((fraction, startValue, endValue) -> {
            PointF starPoint = (PointF) startValue;
            PointF endPoint = (PointF) endValue;
            float x = starPoint.x + fraction * (endPoint.x - starPoint.x);
            float y = starPoint.y + fraction * (endPoint.y - starPoint.y);
            PointF pointF = new PointF(x, y);
            return pointF;
        });
        valueAnimator.setDuration(5000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(animation -> {
            PointF pointF = (PointF) animation.getAnimatedValue();
            mImageView.setX(pointF.x);
            mImageView.setY(pointF.y);
//            int x= (int) pointF.x;
//            int y= (int) pointF.y;
//            mImageView.scrollTo(x,y);
            // getAnimatedFraction 获取动画当前时间流逝的百分比，范围在0~1之间
            if (animation.getAnimatedFraction() == 1) {
//                startActivity(new Intent(ProgressDialogActivity.this,AnimEffectActivity.class));
            }

        });

        setAnim();

    }

    private void setAnim() {
//        PropertyValuesHolder pro = PropertyValuesHolder.ofFloat("translationX", 0F, 100F);//封装平移X的动画
//        PropertyValuesHolder pro2 = PropertyValuesHolder.ofFloat("rotation", 0F, 360F);//封装旋转360的动画
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(ObjectAnimator.ofPropertyValuesHolder(mButton, pro, pro2));
//        set.setInterpolator(new AccelerateInterpolator());
//        set.setDuration(5000).start();
//        mButton.animate().x(300).rotationBy(270).setDuration(2000).setInterpolator(new  LinearInterpolator()).start();
//        mButton.setOnClickListener(v -> Toast.makeText(this, "hk", Toast.LENGTH_SHORT).show());
        //tween
    }

}
