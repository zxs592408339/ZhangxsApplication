package com.example.administrator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.todaynews.Ads;
import com.example.administrator.todaynews.ConnectionUtils;
import com.example.administrator.todaynews.Imgextra;
import com.example.administrator.todaynews.NewsBean;
import com.example.administrator.todaynews.TodayNews;
import com.example.administrator.zhangxsapplication.R;
import com.google.gson.Gson;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;
import com.warmtel.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class ToDayNewsFragment extends Fragment implements XListView.IXListViewListener {
    private ConnectionUtils mConnectionUtils;
    private String news_type_id = "T1348647909107";
    private int pageNo = 1, pageSize = 20, mTotalPageCount = 20;
    private XListView mTodayNewList;
    private NewsAdapter adapter;
    private SliderLayout mSliderLayout;
    private List<Ads> adsList;
    private View headerView;

    public static Fragment newInstance() {
        return new ToDayNewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_today_news, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mConnectionUtils = new ConnectionUtils(getContext());
        mTodayNewList = (XListView) getView().findViewById(R.id.today_new_list);
        mTodayNewList.setXListViewListener(this);
        mTodayNewList.setPullLoadEnable(true); //上拉加载更多开关
        mTodayNewList.setPullRefreshEnable(true);
        adapter = new NewsAdapter(getContext());
        mTodayNewList.setAdapter(adapter);
        getHeaderView();
        doDownLoad(pageNo);
    }

    public void getHeaderView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        headerView = inflater.inflate(R.layout.slider_layout_view, null);
        mSliderLayout = (SliderLayout) headerView.findViewById(R.id.slider_layout);
        mTodayNewList.addHeaderView(headerView);
    }

    public void setHeaderView() {
        if (mSliderLayout != null) {
            mSliderLayout.removeAllSliders();
        }
        for (Ads ads : adsList) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(ads.getTitle())
                    .image(ads.getImgsrc())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom); //指示器位置
    }

    public void doDownLoad(int pageNo){
        String txtUrl = "http://c.m.163.com/nc/article/headline/" + news_type_id + "/" + (pageNo - 1) * pageSize + "-" + pageSize + ".html";
        mConnectionUtils.asycnConnect(txtUrl, ConnectionUtils.Method.GET, new ConnectionUtils.HttpConnectionInterface() {
            @Override
            public void execute(String content) {
                if (content == null) {
                    Toast.makeText(getContext(), "请求出错!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mTodayNewList.stopLoadMore();
                mTodayNewList.stopRefresh();
                mTodayNewList.setRefreshTime("刚刚");
                getGson(content);
            }
        });
    }

    public void getGson(String content) {
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(content, NewsBean.class);
        List<TodayNews> newsLists = newsBean.getT1348647909107();
        TodayNews todayNews = newsLists.get(0);
        adsList = todayNews.getAds();
        if (pageNo == 1) {
            setHeaderView();
        }
        adapter.setNewList(newsLists);
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mTodayNewList.setPullLoadEnable(true);
        doDownLoad(pageNo);
    }

    @Override
    public void onLoadMore() {
        if (++pageNo > mTotalPageCount) {
            pageNo = mTotalPageCount;
            mTodayNewList.setPullLoadEnable(false);
            Toast.makeText(getContext(), "已到底部", Toast.LENGTH_SHORT).show();
            return;
        }
        doDownLoad(pageNo);
    }

    public class NewsAdapter extends BaseAdapter {
        private static final int ITEM_TYPE_ONE = 0;
        private static final int ITEM_TYPE_TWO = 1;//三张图片
        List<TodayNews> newList = new ArrayList<>();
        LayoutInflater mLayoutInflater;

        public NewsAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setNewList(List<TodayNews> newList) {
            if (pageNo == 1) {
                this.newList = newList;
            } else {
                this.newList.addAll(newList);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return newList.size();
        }

        @Override
        public Object getItem(int position) {
            return newList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            TodayNews todayNews = newList.get(position);
            List<Imgextra> imgextraList = todayNews.getImgextra();

            if (imgextraList == null) {
                return ITEM_TYPE_ONE;
            } else {
                return ITEM_TYPE_TWO;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            if (type == ITEM_TYPE_ONE) {
                return getOneView(position, convertView, parent);
            } else {
                return getTwoView(position, convertView, parent);
            }
        }

        public View getOneView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.show_news_one_layout, null);
                ImageView newImg = (ImageView) convertView.findViewById(R.id.new_img);
                TextView newTitle = (TextView) convertView.findViewById(R.id.new_title_txt);
                TextView newDigest = (TextView) convertView.findViewById(R.id.new_digset_txt);
                TodayNewBeanOne todayNewBean = new TodayNewBeanOne();
                todayNewBean.newImg = newImg;
                todayNewBean.newTitle = newTitle;
                todayNewBean.newDigest = newDigest;
                convertView.setTag(todayNewBean);
            }
            TodayNews todayNew = (TodayNews) getItem(position);
            TodayNewBeanOne todayNewBeanOne = (TodayNewBeanOne) convertView.getTag();
            String httpUrl = todayNew.getImgsrc();
            Picasso.with(getContext()).load(httpUrl).into(todayNewBeanOne.newImg);
            todayNewBeanOne.newTitle.setText(todayNew.getTitle());
            todayNewBeanOne.newDigest.setText(todayNew.getDigest());
            return convertView;
        }

        public View getTwoView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.show_news_two_layout, null);
                TextView newsTitle = (TextView) convertView.findViewById(R.id.news_title_two_txt);
                ImageView imageOneView = (ImageView) convertView.findViewById(R.id.news_img_one);
                ImageView imageTwoView = (ImageView) convertView.findViewById(R.id.news_img_two);
                ImageView imageThreeView = (ImageView) convertView.findViewById(R.id.news_img_three);
                TodayNewBeanTwo todayNewBeanTwo = new TodayNewBeanTwo();
                todayNewBeanTwo.newsTitle = newsTitle;
                todayNewBeanTwo.imageOneView = imageOneView;
                todayNewBeanTwo.imageTwoView = imageTwoView;
                todayNewBeanTwo.imageThreeView = imageThreeView;
                convertView.setTag(todayNewBeanTwo);
            }
            TodayNews todayNews = (TodayNews) getItem(position);
            List<Imgextra> imgextra = todayNews.getImgextra();
            TodayNewBeanTwo todayNewBeanTwo = (TodayNewBeanTwo) convertView.getTag();
            todayNewBeanTwo.newsTitle.setText(todayNews.getTitle());
            Picasso.with(getContext()).load(todayNews.getImgsrc()).into(todayNewBeanTwo.imageOneView);
            Picasso.with(getContext()).load(imgextra.get(0).getImgsrc()).into(todayNewBeanTwo.imageTwoView);
            Picasso.with(getContext()).load(imgextra.get(1).getImgsrc()).into(todayNewBeanTwo.imageThreeView);
            return convertView;
        }

        class TodayNewBeanTwo {
            TextView newsTitle;
            ImageView imageOneView, imageTwoView, imageThreeView;
        }

        class TodayNewBeanOne {
            ImageView newImg;
            TextView newTitle, newDigest;
        }
    }
}
