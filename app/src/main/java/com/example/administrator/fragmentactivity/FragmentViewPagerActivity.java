package com.example.administrator.fragmentactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.fragment.MyTestFragment;
import com.example.administrator.fragment.ShopsFragment;
import com.example.administrator.fragment.ToDayNewsFragment;
import com.example.administrator.zhangxsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewPagerActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private TextView TabOne, TabTwo, TabThree;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view_pager);
        TabOne = (TextView) findViewById(R.id.tab_one_txt);
        TabTwo = (TextView) findViewById(R.id.tab_two_txt);
        TabThree = (TextView) findViewById(R.id.tab_three_txt);
        mViewPager = (ViewPager) findViewById(R.id.fragments_content);

        TabOne.setOnClickListener(this);
        TabTwo.setOnClickListener(this);
        TabThree.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(this);
        TabOne.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        adapter.setDataLists(getDataFragment());
    }

    public List<Fragment> getDataFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        Fragment toDayNewsFragment = new ToDayNewsFragment();
        Fragment shopsFragment = new ShopsFragment();
        Fragment myTestFragment = new MyTestFragment();
        fragmentList.add(toDayNewsFragment);
        fragmentList.add(shopsFragment);
        fragmentList.add(myTestFragment);
        return fragmentList;
    }

    @Override
    public void onClick(View view) {
        clearBackgroundColor();
        switch (view.getId()) {
            case R.id.tab_one_txt:
                mViewPager.setCurrentItem(0);
                TabOne.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.tab_two_txt:
                mViewPager.setCurrentItem(1);
                TabTwo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.tab_three_txt:
                mViewPager.setCurrentItem(2);
                TabThree.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    public void clearBackgroundColor() {
        TabOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TabTwo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TabThree.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        clearBackgroundColor();
        switch (position) {
            case 0:
                TabOne.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 1:
                TabTwo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 2:
                TabThree.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> dataLists = new ArrayList<>();

        private void setDataLists(List<Fragment> list) {
            this.dataLists = list;
            notifyDataSetChanged();
        }

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return dataLists.get(position);
        }

        @Override
        public int getCount() {
            return dataLists.size();
        }
    }
}
