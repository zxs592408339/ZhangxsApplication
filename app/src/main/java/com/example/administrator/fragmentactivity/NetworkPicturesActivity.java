package com.example.administrator.fragmentactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.fragment.NetworkPicturesFragment;
import com.example.administrator.zhangxsapplication.R;

public class NetworkPicturesActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private String[] imageUrls = new String[]{
            "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_pictures);
        mViewPager = (ViewPager) findViewById(R.id.network_view_pager);
        NetworkPicturesAdapter adapter = new NetworkPicturesAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        adapter.setData(imageUrls);
    }

    class NetworkPicturesAdapter extends FragmentStatePagerAdapter {
        String[] imageUrl = new String[]{};

        public NetworkPicturesAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setData(String[] imageUrl) {
            this.imageUrl = imageUrl;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return NetworkPicturesFragment.newInstance(imageUrl[position], position, imageUrls.length);
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }
    }
}
