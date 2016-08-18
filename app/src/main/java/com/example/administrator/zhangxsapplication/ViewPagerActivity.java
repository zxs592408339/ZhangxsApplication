package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener{
    public ViewPager mViewPager;
    public ImageView mImageOneBrn, mImageTwoBrn, mImageThreeBrn;
    List<View> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.pager_view_brn);

        mImageOneBrn = (ImageView) findViewById(R.id.pager_one_brn);
        mImageOneBrn.setImageResource(R.drawable.page_now);
        mImageOneBrn.setOnClickListener(this);

        mImageTwoBrn = (ImageView) findViewById(R.id.pager_two_brn);
        mImageTwoBrn.setOnClickListener(this);

        mImageThreeBrn = (ImageView) findViewById(R.id.pager_three_brn);
        mImageThreeBrn.setOnClickListener(this);


        inflater(R.layout.pager_item1_view);
        inflater(R.layout.pager_item2_view);
        inflater(R.layout.pager_item3_view);

        MyPagerViewAdapter adapter = new MyPagerViewAdapter(list);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mImageOneBrn.setImageResource(R.drawable.page_now);
                        mImageTwoBrn.setImageResource(R.drawable.page);
                        mImageThreeBrn.setImageResource(R.drawable.page);
                        break;
                    case 1:
                        mImageTwoBrn.setImageResource(R.drawable.page_now);
                        mImageOneBrn.setImageResource(R.drawable.page);
                        mImageThreeBrn.setImageResource(R.drawable.page);
                        break;
                    case 2:
                        mImageThreeBrn.setImageResource(R.drawable.page_now);
                        mImageOneBrn.setImageResource(R.drawable.page);
                        mImageTwoBrn.setImageResource(R.drawable.page);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void inflater(int id) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(id, null);
        list.add(v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pager_one_brn:
                mImageOneBrn.setImageResource(R.drawable.page_now);
                mImageTwoBrn.setImageResource(R.drawable.page);
                mImageThreeBrn.setImageResource(R.drawable.page);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.pager_two_brn:
                mImageTwoBrn.setImageResource(R.drawable.page_now);
                mImageOneBrn.setImageResource(R.drawable.page);
                mImageThreeBrn.setImageResource(R.drawable.page);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.pager_three_brn:
                mImageThreeBrn.setImageResource(R.drawable.page_now);
                mImageOneBrn.setImageResource(R.drawable.page);
                mImageTwoBrn.setImageResource(R.drawable.page);
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    public class MyPagerViewAdapter extends PagerAdapter {
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
    }
}