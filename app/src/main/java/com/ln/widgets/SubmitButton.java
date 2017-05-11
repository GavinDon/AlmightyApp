package com.ln.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.ln.view.R;

import static android.view.View.MeasureSpec.getMode;
import static android.view.View.MeasureSpec.getSize;

/**
 * description: 项目提交类型通用button
 * Created by liNan on 2017/5/11 11:02
 */

public class SubmitButton extends AppCompatButton {
    private int btnWidth = 200;
    private int btnHeight = 60;
    private int btnNormalColor = 0x29b6f6;  //btn正常颜色
    private int btnPressColor = 0x81d4fa;  //btn按下颜色

    private int btnAngle = 12;
    private Paint btnPaint;

    public SubmitButton(Context context) {
        this(context, null);
    }

    public SubmitButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubmitButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SubmitButton);
        btnNormalColor = ta.getColor(R.styleable.SubmitButton_btnNormalColor, btnNormalColor);
        btnPressColor = ta.getColor(R.styleable.SubmitButton_btnPressColor, btnPressColor);
        btnAngle = ta.getDimensionPixelSize(R.styleable.SubmitButton_btnAngle, btnAngle);
        ta.recycle();
        initPaint();


    }
    Canvas canvas;
    RectF rf;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas=canvas;
         rf = new RectF(0, 0, btnWidth, btnHeight);
        //rx：x方向上的圆角半径。
        //ry：y方向上的圆角半径。
        canvas.drawRoundRect(rf, btnAngle, btnAngle, btnPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                SubmitButton.this.setSelector();
                SubmitButton.this.setBackgroundColor(btnPressColor);
                break;
        }
        return true;

    }

    private void setSelector() {
//        ColorDrawable cd = new ColorDrawable(btnNormalColor);
//        ColorDrawable cdPress = new ColorDrawable(btnPressColor);
//        GradientDrawable gdNormal=new GradientDrawable();
        ShapeDrawable sdNormal =new ShapeDrawable();
        sdNormal.setShape(new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                paint.setColor(btnNormalColor);
                canvas.drawRoundRect(rf,btnAngle,btnAngle,paint);
            }
        });


//        StateListDrawable s = MyTools.getSelector(gdNormal, gdPress);
//        this.setBackgroundDrawable(s);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = getMode(widthMeasureSpec);
        int heightMode = getMode(heightMeasureSpec);
        int widthSize = getSize(widthMeasureSpec);
        int heightSize = getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            btnWidth = widthSize;
            btnHeight = heightSize;
        } else if (widthMode == MeasureSpec.EXACTLY) {
            btnWidth = widthSize;

        } else if (heightMode == MeasureSpec.EXACTLY) {
            btnHeight = heightSize;
        }
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            btnWidth = 200;
            btnHeight = 60;
        } else if (widthMeasureSpec == MeasureSpec.AT_MOST) {
            btnWidth = 200;
        } else if (heightMeasureSpec == MeasureSpec.AT_MOST) {
            btnHeight = 60;
        }
        Log.i("dd", btnWidth + "==" + btnHeight);
        setMeasuredDimension(btnWidth, btnHeight);
    }

    /**
     * 初始化button参数
     */
    private void initPaint() {
        btnPaint = new Paint();
        btnPaint.setAntiAlias(true);
        btnPaint.setStyle(Paint.Style.FILL);
        btnPaint.setDither(true);
        btnPaint.setStrokeCap(Paint.Cap.ROUND);
        btnPaint.setColor(btnNormalColor);

    }

}
