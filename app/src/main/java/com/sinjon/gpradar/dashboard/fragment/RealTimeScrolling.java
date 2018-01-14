package com.sinjon.gpradar.dashboard.fragment;

import android.graphics.Color;
import android.os.Handler;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Random;

/**
 * @author SongJian
 * @Date 2018/1/14
 * @Email songjian0x00@163.com
 */

public class RealTimeScrolling extends BaseExample {
    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private double graphLastXValue = 5d;
    private PointsGraphSeries<DataPoint> mSeries;
    private GraphView graphView;

    @Override
    public void initGraph(GraphView graphView) {
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(4);

        graphView.getGridLabelRenderer().setLabelVerticalWidth(100);
        this.graphView = graphView;

//        mSeries = new PointsGraphSeries<>();
////        mSeries.setDrawDataPoints(true);
////        mSeries.setDrawBackground(true);
//        graphView.addSeries(mSeries);
    }


    public void onResume() {
        mTimer = new Runnable() {
            @Override
            public void run() {
                graphLastXValue += 0.25d;
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>();
                for (int i = 0; i < 20; i++) {
//                    mSeries.appendData(new DataPoint(graphLastXValue, i), true, Integer.MAX_VALUE);

                    series1.appendData(new DataPoint(graphLastXValue, i), true, Integer.MAX_VALUE);
                    graphView.addSeries(series1);
                    series1.setShape(PointsGraphSeries.Shape.RECTANGLE);
                    series1.setColor(Color.RED);
                    series1.setSize(10);
                }
                mHandler.postDelayed(this, 330);
            }
        };
        mHandler.postDelayed(mTimer, 1500);
    }

    public void onPause() {
        mHandler.removeCallbacks(mTimer);
    }

    double mLastRandom = 2;
    Random mRand = new Random();
    private double getRandom() {
        return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
    }
}
