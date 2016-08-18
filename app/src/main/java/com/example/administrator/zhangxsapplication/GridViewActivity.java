package com.example.administrator.zhangxsapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private List<ImageResIds> list = new ArrayList();
    public GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        mGridView = (GridView) findViewById(R.id.widget_gridview);
        inintData();
        ImageGridViewAdapter adapter = new ImageGridViewAdapter(this, list);
        mGridView.setAdapter(adapter);
    }

    public void inintData() {
        addItem("1", R.drawable.image);
        addItem("2", R.drawable.image1);
        addItem("3", R.drawable.image2);
        addItem("4", R.drawable.image3);
        addItem("5", R.drawable.image4);
        addItem("6", R.drawable.image5);
        addItem("7", R.drawable.image6);
        addItem("8", R.drawable.image7);
        addItem("9", R.drawable.image8);
        addItem("10", R.drawable.image9);
    }

    public void addItem(String title, int t) {
        ImageResIds mainIntentBean = new ImageResIds(title, t);
        list.add(mainIntentBean);
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
            imageView.setImageResource(item.imageView);
            textView.setText(item.title);
            return convertView;
        }
    }

    public class ImageResIds {
        private String title;
        private int imageView;

        public ImageResIds(String title, int imageView) {
            this.title = title;
            this.imageView = imageView;
        }
    }
}
