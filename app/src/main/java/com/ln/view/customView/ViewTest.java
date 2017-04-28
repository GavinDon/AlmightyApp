package com.ln.view.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * description:
 * Created by liNan on 2017/4/18 14:26
 */

public class ViewTest extends View {


    public ViewTest(Context context) {
        this(context, null);
    }

    public ViewTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST: //wrap_content
                Log.i("AT_MOST", widthSize + "");
                break;
            case MeasureSpec.EXACTLY: //有精确的大小 match_paren or  dp
                Log.i("EXACTLY", widthSize + "");
                break;
            case MeasureSpec.UNSPECIFIED: //未指定尺寸，这种情况不多.目前不知道用于何处
                Log.i("UNSPECIFIED", widthSize + "");
                break;
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawLine(canvas);
        drawText(canvas);
        drawPath(canvas);
        DrawBezierCurve(canvas);
//        canvas.drawArc(rf,270,140,true,paint); //270度或者-90度表示从0点位置开始 因为startAngel是从3点钟方向开始

    }

    /**
     * 设置paint 基本属性
     *
     * @param mPaint
     * @return
     */
    private Paint setPaintStyle(Paint mPaint) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        return mPaint;
    }

    private void drawText(Canvas canvas) {
        Paint paint = setPaintStyle(new Paint());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("李南呀", 100, 100, paint);
    }

    /**
     * 画直线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Paint paint = setPaintStyle(new Paint());
        canvas.drawLine(0, 0, 100, 200, paint);
    }

    /**
     * 根据path 来画View
     *
     * @param canvas
     */
    private void drawPath(Canvas canvas) {
        Path path = new Path();
        Paint paint = setPaintStyle(new Paint());
        paint.setColor(Color.RED);
        path.moveTo(0, 50);
        path.lineTo(100, 50);
        for (int i = 0; i < 5; i++) {
            path.rMoveTo(10, 0); //rMoveTo的r就是relative的意思  相对于前面的位置
            path.rLineTo(100, 0);
        }
        RectF rf = new RectF(0, 25, 100, 75);
        path.addArc(rf, 180, 180); //requireApi 21 21之前创建RectF
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制贝赛尔曲线
     */
    private void DrawBezierCurve(Canvas canvas) {
        Paint paint = setPaintStyle(new Paint());
        Path path=new Path();
        path.quadTo(0,200,200,400);
        canvas.drawPath(path,paint);
    }

}
