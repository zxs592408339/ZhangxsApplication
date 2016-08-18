package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopMenuActivity extends AppCompatActivity {
    public Button mPopMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_meun);
        mPopMenu = (Button) findViewById(R.id.pop_menu_brn);

        //菜单按钮点击事件
        mPopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(PopMenuActivity.this, v);
                popupMenu.inflate(R.menu.my_option_menu);

                //弹窗内容点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.back:
                                Toast.makeText(PopMenuActivity.this, "返回成功", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.flip:
                                Toast.makeText(PopMenuActivity.this, "哈哈哈", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
