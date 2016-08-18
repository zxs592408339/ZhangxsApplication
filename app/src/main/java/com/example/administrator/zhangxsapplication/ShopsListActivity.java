package com.example.administrator.zhangxsapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.Gion.Around;
import com.example.administrator.Gion.Info;
import com.example.administrator.Gion.Merchant;
import com.example.administrator.httpConnectionUtils.ConnectionUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShopsListActivity extends AppCompatActivity {
    String txtUrl = "http://192.168.5.7/around";
    ConnectionUtils mConnectionUtils;
    public ListView mInternetListView;
    MerchantKeyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);
        mConnectionUtils = new ConnectionUtils(this);
        mInternetListView = (ListView) findViewById(R.id.internet_list_view);
        adapter = new MerchantKeyAdapter(this);
        mInternetListView.setAdapter(adapter);
        doDownLoad();
    }

    public void doDownLoad() {
        mConnectionUtils.asycnConnect(txtUrl, ConnectionUtils.Method.GET, new ConnectionUtils.HttpConnectionInterface() {
            @Override
            public void execute(String content) {
                Gson gson = new Gson();
                Around around = gson.fromJson(content, Around.class);
                Info info = around.getInfo();
                List<Merchant> merchants = info.getMerchants();
                adapter.setList(merchants);
            }
        });
    }

    public class MerchantKeyAdapter extends BaseAdapter {
        List<Merchant> list = new ArrayList<>();
        LayoutInflater layoutInflater;

        public MerchantKeyAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        public void setList(List<Merchant> list) {
            this.list = list;
            notifyDataSetChanged();
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
                convertView = layoutInflater.inflate(R.layout.show_view, null);
                ImageView internetImg = (ImageView) convertView.findViewById(R.id.internet_img);
                ImageView nearGroupImg = (ImageView) convertView.findViewById(R.id.near_group_img);
                ImageView nearCardImg = (ImageView) convertView.findViewById(R.id.near_card_img);
                ImageView nearTicketImg = (ImageView) convertView.findViewById(R.id.near_ticket_img);
                TextView titleView = (TextView) convertView.findViewById(R.id.internet_title);
                TextView periodView = (TextView) convertView.findViewById(R.id.internet_period);
                TextView addressView = (TextView) convertView.findViewById(R.id.internet_address);
                TextView infoMapView = (TextView) convertView.findViewById(R.id.info_map_txt);
                MerchantKeyBean merchantKeyBean = new MerchantKeyBean();
                merchantKeyBean.internetImg = internetImg;
                merchantKeyBean.nearGroupImg = nearGroupImg;
                merchantKeyBean.nearCardImg = nearCardImg;
                merchantKeyBean.nearTicketImg = nearTicketImg;
                merchantKeyBean.titleView = titleView;
                merchantKeyBean.periodView = periodView;
                merchantKeyBean.addressView = addressView;
                merchantKeyBean.infoMapView = infoMapView;
                convertView.setTag(merchantKeyBean);
            }
            Merchant merchantKey = (Merchant) getItem(position);
            MerchantKeyBean merchantKeyBean = (MerchantKeyBean) convertView.getTag();

            String httpUrl = merchantKey.getPicUrl();
            Glide.with(ShopsListActivity.this).load(httpUrl).into(merchantKeyBean.internetImg);

            merchantKeyBean.nearGroupImg.setImageBitmap(merchantKey.getGroupType().equals("YES") ? BitmapFactory.decodeResource(getResources(), R.drawable.near_group) : null);
            merchantKeyBean.nearCardImg.setImageBitmap(merchantKey.getCardType().equals("YES") ? BitmapFactory.decodeResource(getResources(), R.drawable.near_card) : null);
            merchantKeyBean.nearTicketImg.setImageBitmap(merchantKey.getCouponType().equals("YES") ? BitmapFactory.decodeResource(getResources(), R.drawable.near_ticket) : null);
            merchantKeyBean.titleView.setText(merchantKey.getName());
            merchantKeyBean.periodView.setText(merchantKey.getCoupon());
            merchantKeyBean.addressView.setText(merchantKey.getLocation());
            merchantKeyBean.infoMapView.setText(merchantKey.getDistance());
            return convertView;
        }

    }

    class MerchantKeyBean {
        ImageView internetImg, nearGroupImg, nearCardImg, nearTicketImg;
        TextView titleView, periodView, addressView, infoMapView;
    }
}


