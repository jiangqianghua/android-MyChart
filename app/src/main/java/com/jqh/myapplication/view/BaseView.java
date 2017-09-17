package com.jqh.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jqh.myapplication.R;

/**
 * Created by jiangqianghua on 17/9/17.
 */

public abstract class BaseView extends View {

    protected Context mContext;
    protected Paint mPaint;

    protected int width;
    protected int height;
    // 一下是我们要绘制坐标轴的原点位置
    protected int originlx = 60;
    protected int originly = 800;


    protected String mGraphTitle;
    protected String mXAxisName = "x";
    protected String mYAxisName = "y";
    protected float mAxisTextSize;
    protected int mAxisTextColor;
    // 刻度等分
    protected int axisDividedSizeX = 10 ;
    protected int axisDividedSizeY = 10 ;

    // 刻度最大值
    protected int maxAxisValue = 100;

    // 存放柱条信息，第二个纬度1存放颜色值,0存放柱条的高度
    protected int[][] coumInfo ;
    public BaseView(Context context) {
        this(context, null);
    }


    public BaseView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        // 获取自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.JQHChart);

        mGraphTitle = typedArray.getString(R.styleable.JQHChart_graphTitle);
        mAxisTextColor = typedArray.getColor(R.styleable.JQHChart_axisTextColor,
                context.getResources().getColor(android.R.color.black));
        mAxisTextSize = typedArray.getDimension(R.styleable.JQHChart_axisTextSize,
                12);
        if (typedArray != null) {
            typedArray.recycle();
        }

        initPaint();
    }

    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth() - originlx - 20;
        height = (originly > getHeight() ? getHeight() : originly) - 400;

        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        drawTitle(canvas, mPaint);
        drawXAxisScale(canvas, mPaint);
        drawXAxisScaleValue(canvas, mPaint);
        drawYAxisScale(canvas, mPaint);
        drawYAxisScaleValue(canvas, mPaint);
        drawXAxisArrow(canvas, mPaint);
        drawYAxisArrow(canvas, mPaint);
        drawColunm(canvas, mPaint);
    }

    /**
     * 绘制图表标题
     *
     * @param canvas
     * @param mPaint
     */
    private void drawTitle(Canvas canvas, Paint mPaint) {
        if (!TextUtils.isEmpty(mGraphTitle)) {
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setColor(mAxisTextColor);
            mPaint.setFakeBoldText(true);
            canvas.drawText(mGraphTitle, (getWidth() / 2) - (mPaint.measureText(mGraphTitle)) / 2,
                    originlx + 40, mPaint);
        }
    }

    /**
     * 绘制X坐标轴
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxis(Canvas canvas, Paint mPaint);

    /**
     * 绘制y坐标轴
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxis(Canvas canvas, Paint mPaint);

    /**
     * 绘制X坐标轴刻度
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxisScale(Canvas canvas, Paint mPaint);

    /**
     * 绘制X坐标轴刻度值
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxisScaleValue(Canvas canvas, Paint mPaint);

    /**
     * 绘制y坐标轴刻度
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScale(Canvas canvas, Paint mPaint);

    /**
     * 绘制y坐标轴刻度值
     *
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScaleValue(Canvas canvas, Paint mPaint);

    /**
     * 绘制X坐标轴箭头
     *
     * @param canvas
     * @param mPaint
     */
    protected void drawXAxisArrow(Canvas canvas, Paint mPaint) {
        Path mpathY = new Path();
        mpathY.moveTo(originlx,originly-height-30);
        mpathY.lineTo(originlx-10,originly-height);
        mpathY.lineTo(originlx+10,originly-height);
        mpathY.close();
        canvas.drawPath(mpathY,mPaint);
        canvas.drawText(mYAxisName,originlx-50,originly-height-30,mPaint);
    }

    /**
     * 绘制y坐标轴箭头
     *
     * @param canvas
     * @param mPaint
     */
    protected void drawYAxisArrow(Canvas canvas, Paint mPaint)
    {
        Path mpathX = new Path();
        mpathX.moveTo(originlx+width+30,originly);
        mpathX.lineTo(originlx+width,originly+10);
        mpathX.lineTo(originlx+width,originly-10);
        mpathX.close();  // 封闭

        canvas.drawPath(mpathX,mPaint);
        canvas.drawText(mXAxisName,originlx+width,originly+50,mPaint);

   }


    /**
     * 绘制柱条
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawColunm(Canvas canvas,Paint mPaint);

    public void setCoumInfo(int[][] coumInfo) {
        this.coumInfo = coumInfo;
    }

    /**
     *
     * @param maxValue 最大值
     * @param scaleNum 分多少刻度
     */
    public void setXAxisScaleValue(int maxValue,int scaleNum)
    {
        maxAxisValue = maxValue ;
        axisDividedSizeX = scaleNum ;
    }

    /**
     *
     * @param maxValue 最大值
     * @param scaleNum 分多少刻度
     */
    public void setYAxisScaleValue(int maxValue,int scaleNum)
    {
        maxAxisValue = maxValue ;
        axisDividedSizeY = scaleNum ;
    }
}

