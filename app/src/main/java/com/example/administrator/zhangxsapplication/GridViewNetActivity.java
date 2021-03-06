package com.example.administrator.zhangxsapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GridViewNetActivity extends AppCompatActivity {
    private GridView mGridView;
    private String[] imageThumbUrls = new String[]{
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

    public static final Executor mExec = new ThreadPoolExecutor(15, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_net);
        mGridView = (GridView) findViewById(R.id.grid_view_net);

        GridViewNetAdapter mGridViewNetAdapter = new GridViewNetAdapter(this, imageThumbUrls);
        mGridView.setAdapter(mGridViewNetAdapter);
    }

    class GridViewNetAdapter extends BaseAdapter{
        private String[] imageThumbUrls = new String[]{};
        private LayoutInflater mLayoutInflater;

        public GridViewNetAdapter(Context context,String[] imageThumbUrls){
            mLayoutInflater = LayoutInflater.from(context);
            this.imageThumbUrls = imageThumbUrls;
        }
        @Override
        public int getCount() {
            return imageThumbUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageThumbUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ImageView imageView;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.item_gridview_net_layout, null);
                imageView = (ImageView) convertView.findViewById(R.id.net_image_view);
                convertView.setTag(imageView);
            } else {
                imageView = (ImageView) convertView.getTag();
            }

            String httpUrl = (String) getItem(position);

            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    String httpUrl = params[0];
                    Bitmap bitmap = doDownLoadPicture(httpUrl);
                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,httpUrl);


            return convertView;
        }
        public Bitmap doDownLoadPicture(String httpUrl) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(httpUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
