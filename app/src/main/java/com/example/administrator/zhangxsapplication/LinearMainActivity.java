package com.example.administrator.zhangxsapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.animation.MyAnimationActivity;
import com.example.administrator.charting.chartingactivity.MyControlActivity;
import com.example.administrator.charting.chartingactivity.MyRelativeLayoutActivity;
import com.example.administrator.fragmentactivity.ArgumentActivity;
import com.example.administrator.fragmentactivity.FragmentStackActivity;
import com.example.administrator.fragmentactivity.FragmentViewPagerActivity;
import com.example.administrator.fragmentactivity.MyFragmentActivity;
import com.example.administrator.fragmentactivity.MyTestFragmentActivity;
import com.example.administrator.fragmentactivity.NetworkPicturesActivity;
import com.example.administrator.fragmentactivity.SubFragmentMainActivity;
import com.example.administrator.fragmentactivity.ToDayNewsFragmentActivity;
import com.example.administrator.todaynews.TodayNewsActivity;
import com.example.administrator.weixin.WeiXinActivity;

import java.util.ArrayList;
import java.util.List;

public class LinearMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<MainIntentBean> list = new ArrayList();
    public ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_view);
        mListView = (ListView) findViewById(R.id.list_item_view);
        intData();
        MainIntentAdapter adapter = new MainIntentAdapter(this, list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    public void intData() {
        addItem("接口实现", ButtonActivity.class);
        addItem("信息传递(隐示)", InputInformationActivity.class);
        addItem("打电话", IntentActivity.class);
        addItem("数组适配器", ListViewActivity.class);
        addItem("list适配器", ListViewSimpenActivity.class);
        addItem("界面转跳", Main3Activity.class);
        addItem("网格控件", GridViewActivity.class);
        addItem("美团", MeiTuanActivity.class);
        addItem("输入提示框", AutoCompletTextActivity.class);
        addItem("进度条", ProgressBarActivity.class);
        addItem("Toolbar工具条+上下文菜单(长按弹出)", MyTooBarActivity.class);
        addItem("菜单栏", MenuOptionActivity.class);
        addItem("菜单按钮", PopMenuActivity.class);
        addItem("对话框+消息处理机制", AlertDialogActivity.class);
        addItem("水平滑动", ViewPagerActivity.class);
        addItem("TabLayout水平滑动",TabLayoutActivity.class);
        addItem("列表弹框", PopupWindowActivity.class);
        addItem("页卡布局切换界面", TabMainActivity.class);
        addItem("数据库的操作",SQLiteActivity.class);
        addItem("学生管理", StudentManagementActivity.class);
        addItem("数据库安卓操作+信息提供者",QSLiteAndroidActivity.class);
        addItem("微信(登录信息存储)", WeiXinActivity.class);
        addItem("IO流读写数据",IOActivity.class);
        addItem("扫描内存中的文件",ScanFileActivity.class);
        addItem("音乐播放",MusicActivity.class);
        addItem("后台服务",MyServiceActivity.class);
        addItem("线程服务模拟下载文件",MyServiceDownFileActivity.class);
        addItem("网络获取图片",InternetImageActivity.class);
        addItem("异步多线程取网络图片",GridViewNetActivity.class);
        addItem("放缩图片",BitmapUtilsActivity.class);
        addItem("网络获取数据",HttpTextActivity.class);
        addItem("浏览器",WebViewActivity.class);
        addItem("商店List",ShopsListActivity.class);
        addItem("今日新闻",TodayNewsActivity.class);
        addItem("Fragment",MyFragmentActivity.class);
        addItem("动态添加Fragment",ToDayNewsFragmentActivity.class);
        addItem("Fragment生命周期测试",MyTestFragmentActivity.class);
        addItem("Fragment → Activity → Fragment传参",ArgumentActivity.class);
        addItem("FragmentTab",FragmentViewPagerActivity.class);
        addItem("压栈&弹栈",FragmentStackActivity.class);
        addItem("Fragment的子集",SubFragmentMainActivity.class);
        addItem("FragmentTab加载网络图片",NetworkPicturesActivity.class);
        addItem("自定义控件",MyControlActivity.class);
        addItem("自定义相对控件",MyRelativeLayoutActivity.class);
        addItem("动画",MyAnimationActivity.class);
    }

    public <T> void addItem(String title, Class<T> t) {
        MainIntentBean mainIntentBean = new MainIntentBean(title, t);
        list.add(mainIntentBean);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainIntentAdapter adapter = (MainIntentAdapter) parent.getAdapter();
        MainIntentBean mainIntentBean = (MainIntentBean) adapter.getItem(position);
        Intent intent = new Intent(this, mainIntentBean.toActivityClass);
        startActivity(intent);
    }

    public class MainIntentAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private List<MainIntentBean> list = new ArrayList();

        public MainIntentAdapter(Context context, List<MainIntentBean> list) {
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
                convertView = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
                TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(textView);
            }
            TextView textView = (TextView) convertView.getTag();

            MainIntentBean item = (MainIntentBean) getItem(position);
            textView.setText(item.title);
            return convertView;
        }
    }

    public class MainIntentBean<T> {
        private String title;
        private Class<T> toActivityClass;

        public MainIntentBean(String title, Class<T> toActivityClass) {
            this.title = title;
            this.toActivityClass = toActivityClass;
        }
    }
}
