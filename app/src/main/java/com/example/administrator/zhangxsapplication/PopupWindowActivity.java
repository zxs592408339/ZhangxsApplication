package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dem.MySimpleBaseAdapter;

import java.util.ArrayList;

public class PopupWindowActivity extends AppCompatActivity {
public TextView mPopupWindowBrn;
    ListViewSimpenActivity listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mPopupWindowBrn = (TextView) findViewById(R.id.popup_window);

        LayoutInflater inflater = LayoutInflater.from(this);

        // TODO: 2016/6/12  从ListViewSimpenActivity类中获取数据源
        listView = new ListViewSimpenActivity();
        ArrayList list = listView.listData();

        // TODO: 2016/6/12  将适配器和数据关联
        View popupContentView = inflater.inflate(R.layout.layout_list_view,null);
        ListView popupListView = (ListView) popupContentView.findViewById(R.id.list_item_view);
        MySimpleBaseAdapter adapter = new MySimpleBaseAdapter(this,list);
        popupListView.setAdapter(adapter);

        // TODO: 2016/6/12  弹出适配器中的类容
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setContentView(popupContentView);
        popupWindow.setWidth(1000);
        popupWindow.setHeight(800);

        // TODO: 2016/6/12  点击其他位置取消跳窗
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);


        mPopupWindowBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopupWindowActivity.this,"弹出框",Toast.LENGTH_SHORT).show();
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAsDropDown(v);
                }
            }
        });
    }
}
