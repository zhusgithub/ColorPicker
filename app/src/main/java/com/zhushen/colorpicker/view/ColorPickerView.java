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
public class ColorPickerView extends View{
    private Paint mPaint;

//    private LinearGradient linearGradient;

    private int width,height;

    private int color;

    private int flag =0;

    public ColorPickerView(Context context) {
        this(context,null);
    }

    public ColorPickerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
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

//        int[] hue = new int[361];
//        int count = 0;
//        for(int i = hue.length-1;i>=0;i--,count++){
//            hue[count] = Color.HSVToColor(new float[]{i,1.f,1.f});
//        }
//        LinearGradient linearGradient = new LinearGradient(0,0,0,height,hue,null, Shader.TileMode.REPEAT);
//
//        mPaint.setShader(linearGradient);
        LinearGradient linearGradient = new LinearGradient(0,0,0,height,color,0xffffffff, Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);


        canvas.drawRect(rect,mPaint);
    }

    public void setLinearGradient(int color){
//        LinearGradient linearGradient = new LinearGradient(0,0,0,height,color,0x00000000, Shader.TileMode.REPEAT);
//        mPaint.setShader(linearGradient);
        this.color = color;
        invalidate();
    }

    public void setLinearGradient(int[] colors){
        LinearGradient linearGradient = new LinearGradient(0,0,0,height,colors,null, Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);
        flag ++;
        postInvalidate();
    }
}
