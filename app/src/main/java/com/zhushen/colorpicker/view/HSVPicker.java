package com.zhushen.colorpicker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Zhushen on 2018/5/8.
 */
public class HSVPicker extends View {
    private Paint mPaint;

    private int width,height;

    private float[] hsv = {1.f,1.f,1.f};

    public HSVPicker(Context context) {
        this(context,null);
    }

    public HSVPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HSVPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect = new Rect(0,0,width,height);
        int[] hue = new int[361];
        int count = 0;
        for(int i = hue.length-1;i>=0;i--,count++){
            hue[count] = Color.HSVToColor(new float[]{i,1.f,1.f});
        }
        LinearGradient mHueShader = new LinearGradient(rect.left,rect.top,rect.left,rect.bottom,hue,null, Shader.TileMode.REPEAT);
        mPaint.setShader(mHueShader);
        canvas.drawRect(rect,mPaint);
    }


    private void setHSVColor(float color){
        hsv[0] = color;
        invalidate();
    }
}
