package com.example.administrator.zhangxsapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {
    public TabLayout mTabLayout;
    public ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.tab_view_pager);
        List<View> pagerLIst = new ArrayList<>();
        setData(pagerLIst);
        MyPagerViewAdapter adapter = new MyPagerViewAdapter(pagerLIst);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void setData(List<View> pagerList){
        LayoutInflater inflater = LayoutInflater.from(this);
        View v1 =inflater.inflate(R.layout.pager_item1_view,null);
        View v2 =inflater.inflate(R.layout.pager_item2_view,null);
        View v3 =inflater.inflate(R.layout.pager_item3_view,null);
        pagerList.add(v1);
        pagerList.add(v2);
        pagerList.add(v3);
    }
    public class MyPagerViewAdapter extends PagerAdapter {
        String[] strings ={"美女1","美女2","美女3"};
        List<View> list = new ArrayList<>();

        public MyPagerViewAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = list.get(position);
            container.removeView(v);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }
    }
}
