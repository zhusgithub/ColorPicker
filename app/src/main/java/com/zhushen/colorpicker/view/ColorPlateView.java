package com.zhushen.colorpicker.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dingmouren on 2017/5/4.
 */

public class ColorPlateView extends View {
    private static final String TAG = ColorPlateView.class.getName();
    private Paint mPaint,pointPaint;
    private LinearGradient mShaderVertical;
    private final float[] HSV = {1.f,1.f,1.f};
    private final float[] selectedHSV = {1.f,1.f,1.f};

    private int width,height;
    private int pointX,pointY;

    public ColorPlateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorPlateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        init();
    }

    private void init() {
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStrokeWidth(3);
        pointPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        pointY = 0;
        pointX = width;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null){
            mPaint = new Paint();
            mShaderVertical = new LinearGradient(0.f,0.f,0.f,this.getMeasuredHeight(),0xffffffff,0xff000000, Shader.TileMode.CLAMP);//线性渐变
        }
        int rgb = Color.HSVToColor(HSV);
        LinearGradient shaderHorizontal = new LinearGradient(0.f,0.f,this.getMeasuredWidth(),0.f,0xffffffff,rgb,Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(mShaderVertical,shaderHorizontal,PorterDuff.Mode.MULTIPLY );//混合渐变
        mPaint.setShader(composeShader);
        canvas.drawRect(0.f,0.f,this.getMeasuredWidth(),this.getMeasuredHeight(),mPaint);

        drawPoint(canvas);
    }

    private void drawPoint(Canvas canvas) {
        canvas.drawCircle(pointX,pointY,10,pointPaint);
        canvas.drawPoint(pointX,pointY,pointPaint);
    }

    /**
     * 设置色彩
     * @param hue
     */
    public void setHue(float hue){
        HSV[0] = hue;
        returnCurrentColor();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                pointX = x;
                pointY = y;
                returnCurrentColor();
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }


    public interface ColorChangedListener{
        void onRGBChanged(int color);
    }

    ColorChangedListener colorChangedListener;

    public void registerColorChangedListener(ColorChangedListener colorChangedListener){
        this.colorChangedListener = colorChangedListener;
    }

    private void returnCurrentColor(){
        selectedHSV[0] = HSV[0];
        selectedHSV[1] = (float) pointX/width;
        selectedHSV[2] = (float) (height -pointY)/height;
        int rgb = Color.HSVToColor(selectedHSV);
        if(colorChangedListener != null){
            colorChangedListener.onRGBChanged(rgb);
        }
    }

}
