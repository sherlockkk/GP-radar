package com.sinjon.gpradar.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.sinjon.gpradar.R;
import com.sinjon.gpradar.about.fragment.AboutFragment;
import com.sinjon.gpradar.base.BaseActivity;
import com.sinjon.gpradar.dashboard.fragment.DashboardFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SongJian
 * @Date 2018/1/12
 * @Email songjian0x00@163.com
 */
public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    Fragment dashboard = new DashboardFragment();
    Fragment about = new AboutFragment();
    private List<Fragment> fragmentLists = Arrays.asList(dashboard, about);
    int cursor = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    cursor = 0;
                    addFragmentToStack(cursor);
                    return true;
                case R.id.navigation_about:
                    cursor = 1;
                    addFragmentToStack(cursor);
                    return true;
            }
            addFragmentToStack(cursor);
            return false;
        }
    };

    private void addFragmentToStack(int cursor) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentLists.get(cursor);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_content, fragment);
        }
        for (int i = 0; i < fragmentLists.size(); i++) {
            Fragment f = fragmentLists.get(i);
            if (i == cursor && fragment.isAdded()) {
                transaction.show(f);
            } else if (f != null && f.isAdded() && f.isVisible()) {
                transaction.hide(f);
            }
        }
        transaction.commit(); //允许在活动状态保存后执行提交,如果活动需要从其状态恢复，那么提交就会丢失
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        addFragmentToStack(cursor);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
