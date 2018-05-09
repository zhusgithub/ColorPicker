package com.zhushen.colorpicker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zhushen.colorpicker.view.ColorPickerView;

public class MainActivity extends AppCompatActivity {
    private ColorPickerView colorPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        colorPickerView = (ColorPickerView)findViewById(R.id.colorPicker);

//        int[] hue = new int[361];
//        int count = 0;
//        for(int i = hue.length-1;i>=0;i--,count++){
//            hue[count] = Color.HSVToColor(new float[]{i,1.f,1.f});
//        }
//        colorPickerView.setLinearGradient(hue);

        colorPickerView.setLinearGradient(Color.YELLOW);
    }
}
