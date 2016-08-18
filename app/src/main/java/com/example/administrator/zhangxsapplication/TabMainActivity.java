package com.example.administrator.zhangxsapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

public class TabMainActivity extends TabActivity {
    private TabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);

        mTabHost = getTabHost();

        LayoutInflater inflater = LayoutInflater.from(this);
        View tab_view_one = inflater.inflate(R.layout.tab_view_one_layout,null);
        View tab_view_two = inflater.inflate(R.layout.tab_view_two_layout,null);
        View tab_view_three = inflater.inflate(R.layout.tab_view_three_layout,null);

        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec("tab1");
        tabSpec1.setIndicator(tab_view_one);
        tabSpec1.setContent(new Intent(this,MeiTuanActivity .class));

        TabHost.TabSpec tabSpec2 = mTabHost.newTabSpec("tab2");
        tabSpec2.setIndicator(tab_view_two);
        tabSpec2.setContent(new Intent(this, GridViewActivity.class));

        TabHost.TabSpec tabSpec3 = mTabHost.newTabSpec("tab3");
        tabSpec3.setIndicator(tab_view_three);
        tabSpec3.setContent(new Intent(this, AlertDialogActivity.class));

        mTabHost.addTab(tabSpec1);
        mTabHost.addTab(tabSpec2);
        mTabHost.addTab(tabSpec3);
    }
}
