package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.dem.MySimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
public ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view);
        mListView = (ListView)findViewById(R.id.list_item_view);

        List<String> list = new ArrayList<>();
        list.add("数学");
        list.add("语文");
        list.add("物理");
        list.add("生物");
        list.add("化学");
        MySimpleAdapter adapter = new MySimpleAdapter(this,list);
//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_view,arrays);
        mListView.setAdapter(adapter);
        }
}
