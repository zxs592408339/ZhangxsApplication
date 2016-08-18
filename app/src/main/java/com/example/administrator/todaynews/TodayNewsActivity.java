package com.example.administrator.todaynews;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.fragment.MenuFragment;
import com.example.administrator.fragment.ToDayNewsFragment;
import com.example.administrator.zhangxsapplication.R;
import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingActivity;

public class TodayNewsActivity extends SlidingActivity implements MenuFragment.OnMenuClickListener{
    SlidingMenu slidingMenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_news);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.today_news,ToDayNewsFragment.newInstance())
                .commit();

        setBehindContentView(R.layout.sliding_menu_layout);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sliding_menu_layout, MenuFragment.newInstance())
                .commit();

        slidingMenu = getSlidingMenu();
        slidingMenu.setSlidingEnabled(true);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_off_width);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setMode(SlidingMenu.LEFT);
    }

    @Override
    public void onMenuClick(View v) {
        switch (v.getId()) {
            case R.id.menu_one_brn:
                Toast.makeText(TodayNewsActivity.this,"按钮1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_two_brn:
                Toast.makeText(TodayNewsActivity.this,"按钮2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_three_brn:
                Toast.makeText(TodayNewsActivity.this,"按钮3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_four_brn:
                Toast.makeText(TodayNewsActivity.this,"按钮4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_five_brn:
                Toast.makeText(TodayNewsActivity.this,"按钮5",Toast.LENGTH_SHORT).show();
                break;
        }
        slidingMenu.toggle();
    }
}
