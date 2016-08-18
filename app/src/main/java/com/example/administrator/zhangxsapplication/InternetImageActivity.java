package com.example.administrator.zhangxsapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class InternetImageActivity extends AppCompatActivity implements View.OnClickListener {
    private List<ImageResIds> list = new ArrayList<>();
    public GridView mInternetGridView;
    public Button mInternetBrn, mInternetAsyncTaskBrn;
    Handler mHandler = new Handler();
    ImageGridViewAdapter adapter;
    String[] imageThumbUrls = new String[]{
            "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_image);
        mInternetGridView = (GridView) findViewById(R.id.internet_image_grid_view);
        mInternetBrn = (Button) findViewById(R.id.internet_brn);
        mInternetAsyncTaskBrn = (Button) findViewById(R.id.internet_async_task_brn);
        mInternetBrn.setOnClickListener(this);
        mInternetAsyncTaskBrn.setOnClickListener(this);
        adapter = new ImageGridViewAdapter(this, list);
        mInternetGridView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.internet_brn:
                showHttpPicture();
                break;
            case R.id.internet_async_task_brn:
                getInternetImageAsyncTask();
                break;
        }
    }

    public void addItem(String title, Bitmap t) {
        ImageResIds mainIntentBean = new ImageResIds();
        mainIntentBean.setImageView(t);
        mainIntentBean.setTitle(title);
        list.add(mainIntentBean);
    }

    public void showHttpPicture() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, length = imageThumbUrls.length; i < length; i++) {
                    try {
                        URL url = new URL(imageThumbUrls[i]);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        InputStream is = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        addItem("" + (i + 1), bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void getInternetImageAsyncTask() {
        for (int i = 0, length = imageThumbUrls.length; i < length; i++) {
            final int finalI = i;
            AsyncTask<String, Void, Bitmap> asyncTask = new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    return getBitmapByHttp(params[0]);
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    addItem("" + (finalI + 1), bitmap);
                    adapter.notifyDataSetChanged();
                }
            };
            asyncTask.execute(imageThumbUrls[i]);
        }
    }


    public Bitmap getBitmapByHttp(String httpUrl) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public class ImageGridViewAdapter extends BaseAdapter {
        private List<ImageResIds> list = new ArrayList();
        private LayoutInflater layoutInflater;

        public ImageGridViewAdapter(Context context, List list) {
            layoutInflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.image_grid_layout, null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_image_view);
            TextView textView = (TextView) convertView.findViewById(R.id.grid_text_view);
            ImageResIds item = (ImageResIds) getItem(position);
            imageView.setImageBitmap(item.getImageView());
            textView.setText(item.title);
            return convertView;
        }
    }

    public class ImageResIds {
        private String title;
        private Bitmap imageView;

        public Bitmap getImageView() {
            return imageView;
        }

        public void setImageView(Bitmap imageView) {
            this.imageView = imageView;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}