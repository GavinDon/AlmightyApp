package com.ln.view.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.ln.utils.MyTools;
import com.ln.view.R;

/**
 * description: 圆形进度条。
 * Created by liNan on 2017/5/5 14:24
 */

public class CircleProgressDialog extends View {

    private int roundWidth; //圆宽
    private int roundColor; //圆的颜色
    private Paint mPaint; //圆的画笔
    private Paint progressPaint;
    private RectF mRectF;
    private int deviceWidth;
    private int deviceHeight;
    private int[] mColors = new int[]{0xffCD8500, 0xFF76EEC6, 0xFF79CDCD,};
    private SweepGradient mSweepGradient;
    private int mRadius;

    private float textSize = 12;
    private Paint textPaint;
    private float textWidht;

    public CircleProgressDialog(Context context) {
        this(context, null);
    }

    public CircleProgressDialog(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        roundColor = ta.getColor(R.styleable.RoundProgressBar_circleColor, 0);
        roundWidth = ta.getInteger(R.styleable.RoundProgressBar_circleSize, 1);
        ta.recycle();
        deviceWidth = MyTools.deviceInch()[0];
        deviceHeight = MyTools.deviceInch()[1];
        mRadius = deviceWidth / 4 - 40; //圆半径
        init();

    }

    /**
     * 固定不动的圆
     */
    private void init() {
        //----外层圆paint-----//
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(roundColor);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        //----进度条paint-----//
        progressPaint = new Paint();
        mSweepGradient = new SweepGradient(deviceWidth / 2, deviceHeight / 2, mColors, null);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeWidth(roundWidth);
        progressPaint.setStyle(Paint.Style.STROKE); //设置空心
        progressPaint.setColor(Color.BLUE);
        progressPaint.setShader(mSweepGradient);
        //根据进度画扇形的rectF;
        mRectF = new RectF(deviceWidth / 2 - mRadius + 5, deviceHeight / 2 - mRadius + 5, deviceWidth / 2 + mRadius - 5, deviceHeight / 2 + mRadius - 5);
        textPaint = new Paint();
        textPaint.setTextSize(MyTools.sp2px(this.getContext(), textSize));
        textPaint.setAntiAlias(true);
        textPaint.setLinearText(true);
        textPaint.setColor(Color.BLACK);
        textWidht = textPaint.measureText("我们的爱情");//计算字符的宽度


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(deviceWidth / 2, deviceHeight / 2, mRadius, mPaint);
        canvas.drawArc(mRectF, 270, 270, false, progressPaint);
        canvas.drawText("我们的爱情", deviceWidth / 2 - textWidht / 2, deviceHeight / 2, textPaint);

    }
}
