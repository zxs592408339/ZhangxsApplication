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

public class StudentAdapter extends BaseAdapter {
    private List<Students> list = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public StudentAdapter(Context c, List<Students> list) {
        this.context = c;
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
        StudentQS student;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_student_list, null);

            TextView textID = (TextView) convertView.findViewById(R.id.student_item_id_view);
            TextView textName = (TextView) convertView.findViewById(R.id.student_item_name_view);
            TextView texAge = (TextView) convertView.findViewById(R.id.student_item_age_view);

            student = new StudentQS();
            student.id = textID;
            student.name = textName;
            student.age = texAge;
            convertView.setTag(student);
        }

        student = (StudentQS) convertView.getTag();
        Students item = (Students) getItem(position);
        student.id.setText(item.getId());
        student.name.setText(item.getName());
        student.age.setText(item.getAge());
        return convertView;
    }
}
