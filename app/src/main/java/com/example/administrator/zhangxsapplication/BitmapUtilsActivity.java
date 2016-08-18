package com.example.administrator.zhangxsapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;


public class BitmapUtilsActivity extends AppCompatActivity implements AdapterView.OnClickListener {
    public ImageView mBitmapImg;
    public Button mDrawableGetImgBrn, mInternetGetImgBrn;
    Handler mHandler = new Handler();
    String httpUrl = "http://dl.bizhi.sogou.com/images/2013/09/10/378139.jpg";
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_utils);
        mBitmapImg = (ImageView) findViewById(R.id.bitmap_img);

        mDrawableGetImgBrn = (Button) findViewById(R.id.drawable_get_img);
        mInternetGetImgBrn = (Button) findViewById(R.id.internet_get_img);
        mDrawableGetImgBrn.setOnClickListener(this);
        mInternetGetImgBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawable_get_img:
                bitmap = BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.hahahaha, 80, 80);
                mBitmapImg.setImageBitmap(bitmap);
                break;
            case R.id.internet_get_img:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bitmap = BitmapUtils.decodeSampleBitmapFromStream(httpUrl, 280, 280);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mBitmapImg.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
                break;
        }
    }
}
