package com.ln.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.ln.utils.MyTools;
import com.ln.view.R;

/**
 * description: 项目提交类型通用button
 * Created by liNan on 2017/5/11 11:02
 */

public class SubmitButton extends AppCompatButton implements View.OnClickListener {
    /**
     * Shape is a rectangle, possibly with rounded corners
     */
    public static final int RECTANGLE = 0;

    private int btnNormalColor = 0x29b6f6;  //btn正常颜色
    private int btnPressColor = 0x81d4fa;  //btn按下颜色
    private int btnStrokeColor = btnNormalColor; //按扭描边颜色
    private int txtNormalColor = Color.WHITE; //默认为白色
    private int txtPressColor=Color.RED;
    private int btnAngle = 0;
    private int shapeType; //


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

        txtNormalColor = ta.getColor(R.styleable.SubmitButton_txtNormalColor, txtNormalColor);
        txtPressColor = ta.getColor(R.styleable.SubmitButton_txtPressColor, txtNormalColor);
        shapeType = ta.getInteger(R.styleable.SubmitButton_shape, RECTANGLE);
        ta.recycle();
        this.setBackgroundDrawable((this.setSelector()));
        this.setTextColor(createColorStateList(txtNormalColor, txtPressColor));
    }

    /**
     * 获取selector
     *
     * @return
     */
    private StateListDrawable setSelector() {
        Drawable normal = getDrawable(btnNormalColor, btnAngle);
        Drawable press = getDrawable(btnPressColor, btnAngle);
        return MyTools.getSelector(normal, press);
    }

    /**
     * 绘制shape
     *
     * @param rgb    shape 的背景色
     * @param radius shape 圆角
     * @return
     */
    private GradientDrawable getDrawable(int rgb, float radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);//设置颜色
        gradientDrawable.setCornerRadius(radius);//设置圆角的半径
        gradientDrawable.setShape(shapeType);
        return gradientDrawable;
    }

    /**
     * 按下时文字颜色
     *
     * @param normalColor 文字正常颜色
     * @param pressColor  获取焦点或者按下旰的颜色
     * @return
     */
    private ColorStateList createColorStateList(int normalColor, int pressColor) {
        //创建colorState的二维数组
        int[][] states = new int[][]{{android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.focusable}, {-android.R.attr.state_pressed}};
        int colors[] = new int[]{pressColor, normalColor};
        ColorStateList mColorStateList = new ColorStateList(states, colors);
        return mColorStateList;
    }

    @Override
    public void onClick(View v) {

    }

    //    private long firstClickTime = 0;
//    private long currentClickTime;
//    private boolean isFirst = true;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                currentClickTime = System.currentTimeMillis();
//                break;
//            case MotionEvent.ACTION_UP:
//                firstClickTime = currentClickTime;
//                break;
//        }
//        if (isFirst) {
//            firstClickTime = currentClickTime;
//            isFirst=false;
//        } else {
//            if (currentClickTime - firstClickTime < 1000) {
//                return true;
//            }
//        }
//        return false;
//    }


}
