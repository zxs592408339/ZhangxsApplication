package com.example.administrator.dem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zhangxsapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySimpleBaseAdapter extends BaseAdapter{
    private List<HashMap<String,Object>> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    public MySimpleBaseAdapter(Context context, List<HashMap<String,Object>> list){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
        View v = layoutInflater.inflate(R.layout.activity_list_view_simpen,null);
        ImageView iconImg = (ImageView) v.findViewById(R.id.list_image_view);
        TextView titleText = (TextView) v.findViewById(R.id.list_text_view_one);
        TextView contentText = (TextView) v.findViewById(R.id.list_text_view_two);

        HashMap<String,Object> item = (HashMap<String,Object>) getItem(position);
        int icon = (int) item.get("icon");
        String title = (String) item.get("title");
        String content = (String) item.get("content");

        iconImg.setImageResource(icon);
//        iconImg.setBackgroundResource(icon);
        titleText.setText(title);
        contentText.setText(content);
        return v;
    }
}
