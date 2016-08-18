package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.dem.MySimpleBaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewSimpenActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view);
        mListView = (ListView) findViewById(R.id.list_item_view);

        ArrayList arraylist = listData();

//        String[] from = {"icon", "title", "content"};
//        int[] to = {R.id.list_image_view, R.id.list_text_view_one, R.id.list_text_view_two};
//        SimpleAdapter adapter = new SimpleAdapter(this, arraylist, R.layout.activity_list_view_simpen, from, to);

        MySimpleBaseAdapter adapter = new MySimpleBaseAdapter(this,arraylist);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    public ArrayList listData() {
        ArrayList arraylist = new ArrayList();
        HashMap<String, Object> item = new HashMap();
        item.put("icon", R.drawable.image);
        item.put("title", "【多店通用】乡村基");
        item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image1);
        item.put("title", "【淘宝商店】肯德基");
        item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image2);
        item.put("title", "【京东商城】必胜客");
        item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image3);
        item.put("title", "【天猫商城】全家桶");
        item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image4);
        item.put("title", "【多店通用】廖记棒棒鸡");
        item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image5);
        item.put("title", "【幸福大道】囧囧串串");
        item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image6);
        item.put("title", "【大盘鸡店】乡村基");
        item.put("content", "20元代金券！全场通用，可叠加使用，提供免费WiFi！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image7);
        item.put("title", "【王氏火锅】廖记棒棒鸡");
        item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image8);
        item.put("title", "【傻子店通用】九锅一堂");
        item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
        arraylist.add(item);

        item = new HashMap();
        item.put("icon", R.drawable.image9);
        item.put("title", "【幸福大道】囧囧串串");
        item.put("content", "32元代金券！全场通用，可叠加使用，节假日通用！");
        arraylist.add(item);

        return arraylist;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MySimpleBaseAdapter adapter = (MySimpleBaseAdapter) parent.getAdapter();
        HashMap<String,Object> item = (HashMap<String, Object>) adapter.getItem(position);
        String title = (String) item.get("title");
        Snackbar.make(view,position+title,Snackbar.LENGTH_SHORT).show();
    }
}
