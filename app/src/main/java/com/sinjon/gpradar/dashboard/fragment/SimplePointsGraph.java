package com.sinjon.gpradar.dashboard.fragment;

import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * @author SongJian
 * @Date 2018/1/14
 * @Email songjian0x00@163.com
 */

public class SimplePointsGraph extends BaseExample {
    @Override
    public void initGraph(GraphView graphView) {
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graphView.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
        series.setSize(10);

        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -1),
                new DataPoint(1, 4),
                new DataPoint(2, 2),
                new DataPoint(3, 1),
                new DataPoint(4, 5)
        });
        graphView.addSeries(series2);
        series2.setShape(PointsGraphSeries.Shape.RECTANGLE);
        series2.setColor(Color.RED);
        series2.setSize(10);
    }
}
