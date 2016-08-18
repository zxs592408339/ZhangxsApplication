package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyTooBarActivity extends AppCompatActivity {
    public Toolbar mToolbar;
    public ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_too_bar);


        //上下文菜单设置（长按事件）
        mListView = (ListView) findViewById(R.id.tool_bar_list);
        String[] list = {"菜单", "上下文", "更多"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
        registerForContextMenu(mListView);


        mToolbar = (Toolbar) findViewById(R.id.tool_bar_title);
        //Toolbar解析菜单资源布局
        mToolbar.inflateMenu(R.menu.my_option_menu);
        //Toolbar Menu按钮监听事件
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back:
                        Toast.makeText(MyTooBarActivity.this, "返回成功", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case R.id.flip:
                        Toast.makeText(MyTooBarActivity.this, "翻页成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MyTooBarActivity.this, MeiTuanActivity.class));
                        break;
                }
                return false;
            }
        });
        //Toolbar导航栏监听事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyTooBarActivity.this, "返回", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

   //关联上下文
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_option_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
