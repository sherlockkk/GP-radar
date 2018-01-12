package com.sinjon.gpradar.about.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinjon.gpradar.R;
import com.sinjon.gpradar.base.BaseFragment;

/**
 * @author SongJian
 * @Date 2018/1/12
 * @Email songjian0x00@163.com
 */

public class AboutFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initToolBar(view);
        return view;
    }

    private void initToolBar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_fm_about);
        toolbar.setTitle("关于我");
        mActivity.setSupportActionBar(toolbar);
    }
}
