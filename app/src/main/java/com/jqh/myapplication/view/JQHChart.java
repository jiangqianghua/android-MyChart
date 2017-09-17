package com.jqh.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.lang.reflect.Array;

/**
 * Created by jiangqianghua on 17/9/17.
 */

public class JQHChart extends BaseView {

    public JQHChart(Context context) {
        this(context,null);
    }

    public JQHChart(Context context, AttributeSet attrs) {
        this(context,attrs,-1);
    }

    public JQHChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(originlx,originly,originlx+width,originly,mPaint);

    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(originlx,originly,originlx,originly-height,mPaint);
    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint mPaint) {
        float cellWidth = width/axisDividedSizeX;
        for(int i = 0 ; i < axisDividedSizeX-1 ; i++)
        {
            canvas.drawLine(cellWidth*(i+1)+originlx,originly,cellWidth*(i+1)+originlx,originly-10,mPaint);
        }
    }

    @Override
    protected void drawXAxisScaleValue(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(16);
        mPaint.setFakeBoldText(true); // 粗体
        float cellWidth = width/axisDividedSizeX ;
        for(int i = 0 ; i < axisDividedSizeX ; i++)
        {
            canvas.drawText(i+"",cellWidth*i+originlx-35,originly+30,mPaint);
        }


    }

    @Override
    protected void drawYAxisScale(Canvas canvas, Paint mPaint) {
        float cellWidth = height/axisDividedSizeY;
        for(int i = 0 ; i < axisDividedSizeY-1 ; i++)
        {
                canvas.drawLine(originlx,originly-cellWidth*(i+1),originlx+10,originly-cellWidth*(i+1),mPaint);
        }
    }

    @Override
    protected void drawYAxisScaleValue(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(16);
        mPaint.setFakeBoldText(true); // 粗体
        float cellHeight = height/axisDividedSizeY ;
        float cellValue = maxAxisValue/axisDividedSizeY ;
        for(int i = 0 ; i < axisDividedSizeY ; i++)
        {
            canvas.drawText(cellValue*i + "",originlx-30,originly-cellHeight*i+10,mPaint);
        }
    }

    /**
     * 绘制直方图的柱条
     * @param canvas
     * @param mPaint
     */
    @Override
    protected void drawColunm(Canvas canvas, Paint mPaint) {
        if(coumInfo != null)
        {
            float cellWidth = width/axisDividedSizeX;
            for(int i = 0 ; i < coumInfo.length;i++)
            {
                mPaint.setColor(coumInfo[i][1]);
                float leftTopY = originly - height*(coumInfo[i][0])/axisDividedSizeY;
                canvas.drawRect(originlx+cellWidth*(i+1),leftTopY,originlx+cellWidth*(i+2),originly,mPaint);
            }
        }
    }
}
