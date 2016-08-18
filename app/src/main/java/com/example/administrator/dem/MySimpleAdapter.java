package com.example.administrator.dem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zhangxsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MySimpleAdapter extends BaseAdapter {
    private List<String> list = new ArrayList<>();
//    List<HashMap<String,Object>> lists = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public MySimpleAdapter(Context c,  List<String> l){

        this.context = c;
        layoutInflater = LayoutInflater.from(context);
        list = l;
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
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_view,null);
            TextView textView = (TextView) convertView.findViewById(R.id.text_item_view);
            convertView.setTag(textView);
        }
        TextView textView = (TextView) convertView.getTag();
        String item = (String)getItem(position);
        textView.setText(item);
        return convertView;
    }
}
