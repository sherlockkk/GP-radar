package com.sinjon.gpradar.dashboard.fragment;

import com.jjoe64.graphview.GraphView;

/**
 * @author SongJian
 * @Date 2018/1/14
 * @Email songjian0x00@163.com
 */

public abstract class BaseExample {
    public abstract void initGraph(GraphView graphView);

    public void onPause(){}
    public void onResume(){}
}
