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
import com.sinjon.gpradar.R;
import com.sinjon.gpradar.base.BaseFragment;
import com.sinjon.gpradar.dashboard.custom.CustomScatterShapeRenderer;

import java.util.ArrayList;

/**
 * @author SongJian
 * @Date 2018/1/12
 * @Email songjian0x00@163.com
 */

public class DashboardFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    private ScatterChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Typeface mTfLight;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initToolBar(view);
        initScatterChart(view);
        mTfLight = Typeface.createFromAsset(mActivity.getAssets(), "OpenSans-Light.ttf");
        return view;
    }

    private void initToolBar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_fm_dashboard);
        toolbar.setTitle("控制台");
        mActivity.setSupportActionBar(toolbar);
    }

    private void initScatterChart(View view) {
        mChart = view.findViewById(R.id.scatterchart);
        tvX = (TextView) view.findViewById(R.id.tvXMax);
        tvY = (TextView) view.findViewById(R.id.tvYMax);
        core(view);
    }

    private void core(View view) {


        mSeekBarX = (SeekBar) view.findViewById(R.id.seekBar1);
        mSeekBarX.setOnSeekBarChangeListener(this);

        mSeekBarY = (SeekBar) view.findViewById(R.id.seekBar2);
        mSeekBarY.setOnSeekBarChangeListener(this);

        mChart.getDescription().setEnabled(false);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawGridBackground(false);
        mChart.setTouchEnabled(true);
        mChart.setMaxHighlightDistance(50f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        mChart.setMaxVisibleValueCount(200);
        mChart.setPinchZoom(true);

        mSeekBarX.setProgress(20);
        mSeekBarY.setProgress(30);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setXOffset(5f);

        YAxis yl = mChart.getAxisLeft();
        yl.setTypeface(mTfLight);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        yl.setDrawGridLines(false);

        mChart.getAxisRight().setEnabled(false);

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(mTfLight);
        xl.setSpaceMin(1);
        xl.setDrawGridLines(false);

        onHiddenChanged(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.i("-----", "go: !hidden");
            go();
        }else {
            Log.i("-----", "go: hidden");
        }
    }

    public void go() {
        Log.i("-----", "go: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 1000; i++) {
                    for (int j = 1; j < 30; j++) {
                        final int finalI = i;
                        final int finalJ = j;
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addEntry(finalI, finalJ);
                            }
                        });
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    ArrayList<Entry> yVals1 = new ArrayList<Entry>();
    private void addEntry(float x,float y) {

        Log.i("---------", "addEntry: " + x + " " + y);
        yVals1.add(new Entry(x, y));

        // create a dataset and give it a type
        ScatterDataSet set1 = new ScatterDataSet(yVals1, "DS 1");
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);

        set1.setScatterShapeSize(20f);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        data = new ScatterData(dataSets);
        data.setValueTypeface(mTfLight);

        mChart.setData(data);
        mChart.setVisibleXRangeMaximum(20);
        mChart.moveViewToX(data.getEntryCount()-21);
        mChart.invalidate();
    }

    ScatterData data;

    /*
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvX.setText("" + (mSeekBarX.getProgress()));
        tvY.setText("" + (mSeekBarY.getProgress()));

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 1; i < mSeekBarX.getProgress() + 1; i++) {
            for (int j = 1; j < mSeekBarY.getProgress() + 1; j++) {
                yVals1.add(new Entry(i, j));
            }
        }

        // create a dataset and give it a type
        ScatterDataSet set1 = new ScatterDataSet(yVals1, "DS 1");
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);

        set1.setScatterShapeSize(20f);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        data = new ScatterData(dataSets);
        data.setValueTypeface(mTfLight);

        mChart.setData(data);
        mChart.invalidate();
    }*/

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {

    }
}
