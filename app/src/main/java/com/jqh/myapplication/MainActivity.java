package com.jqh.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jqh.myapplication.view.JQHChart;

public class MainActivity extends AppCompatActivity {

    private JQHChart mJQHChart ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJQHChart = (JQHChart) findViewById(R.id.columview);
        // 模拟数据
        int [][] columInfo = new int[][]{
                {6, Color.BLUE},
                {5, Color.GREEN},
                {7, Color.RED},
                {4, Color.YELLOW},
                {3, Color.LTGRAY},
                {8, Color.DKGRAY},
                {1, Color.BLUE},
        };

        mJQHChart.setCoumInfo(columInfo);
        mJQHChart.setXAxisScaleValue(10,9);
        mJQHChart.setYAxisScaleValue(10,7);
    }
}
