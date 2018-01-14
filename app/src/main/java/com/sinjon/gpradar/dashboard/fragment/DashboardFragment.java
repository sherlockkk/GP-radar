package com.sinjon.gpradar.dashboard.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.sinjon.gpradar.R;
import com.sinjon.gpradar.base.BaseFragment;
import com.sinjon.gpradar.dashboard.custom.CustomScatterShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author SongJian
 * @Date 2018/1/12
 * @Email songjian0x00@163.com
 */

public class DashboardFragment extends BaseFragment {

    private GraphView graphView;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Typeface mTfLight;

    private RealTimeScrolling realTimeScrolling = new RealTimeScrolling();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initToolBar(view);
        initScatterChart(view);

        return view;
    }

    private void initToolBar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_fm_dashboard);
        toolbar.setTitle("控制台");
        mActivity.setSupportActionBar(toolbar);
    }

    private void initScatterChart(View view) {
        graphView = view.findViewById(R.id.scatterchart);
        realTimeScrolling.initGraph(graphView);
        realTimeScrolling.onResume();
//        new SimplePointsGraph().initGraph(graphView);
//        mTfLight = Typeface.createFromAsset(mActivity.getAssets(), "OpenSans-Light.ttf");
        tvX = (TextView) view.findViewById(R.id.tvXMax);
        tvY = (TextView) view.findViewById(R.id.tvYMax);
    }

}
