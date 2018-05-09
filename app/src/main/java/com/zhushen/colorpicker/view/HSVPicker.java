package com.zhushen.colorpicker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by Zhushen on 2018/5/8.
 */
public class HSVPicker extends View {
    private Paint mPaint,pathPaint;

    private int width,height;
    private int offset;
    private int position;

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

        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        pathPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        offset = width/4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHSV(canvas);
        drawCursor(canvas);
    }

    private void drawCursor(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0,position);
        path.lineTo(0,position+offset*2);
        path.lineTo(offset,position+offset);
        path.close();
        canvas.drawPath(path,pathPaint);
    }

    private void drawHSV(Canvas canvas) {
        Rect rect = new Rect(offset,offset,width-offset,height-offset);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");
                break;
        }
        return super.onTouchEvent(event);
    }
}
