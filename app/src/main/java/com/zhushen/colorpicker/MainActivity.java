package com.zhushen.colorpicker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhushen.colorpicker.view.ColorPickerView;
import com.zhushen.colorpicker.view.ColorPlateView;
import com.zhushen.colorpicker.view.HSVPicker;

public class MainActivity extends AppCompatActivity implements HSVPicker.ColorChange {
    private TextView textView;
    private View view;

    private ColorPickerView colorPickerView;
    private HSVPicker hsvPicker;
    private ColorPlateView colorPlateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        textView = (TextView)findViewById(R.id.colorPicker_text);
        view = (View)findViewById(R.id.colorPicker_view);

        colorPickerView = (ColorPickerView)findViewById(R.id.colorPicker);
        colorPickerView.setLinearGradient(Color.YELLOW);

        hsvPicker = (HSVPicker)findViewById(R.id.hsv_picker);
        hsvPicker.registerColorChangeListener(this);

        colorPlateView = (ColorPlateView)findViewById(R.id.color_plate_view);
    }

    @Override
    public void onColorChanged(int color) {
        textView.setTextColor(color);
        view.setBackgroundColor(color);

    }

    @Override
    public void onhueChanged(float hue) {
        colorPlateView.setHue(hue);
    }
}
