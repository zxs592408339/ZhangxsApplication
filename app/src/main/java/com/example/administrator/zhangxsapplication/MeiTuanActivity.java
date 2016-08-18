package com.example.administrator.zhangxsapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MeiTuanActivity extends AppCompatActivity {
    private List<DataResIds> list = new ArrayList();
    public ListView mListView;
    public Spinner mSpinner1, mSpinner2, mSpinner3, mSpinner4;
    String[] strings1 = {"美食", "火锅", "自助餐", "西餐", "川菜", "湘菜", "蛋糕甜点"};
    String[] strings2 = {"全城", "金牛区", "武侯区", "青羊区", "成华区", "高新区", "锦江区", "青白江区"};
    String[] strings3 = {"自能排序", "好评优先", "离我最近", "人均最低"};
    String[] strings4 = {"筛选", "价格", "销量", "评价"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan);
        mListView = (ListView) findViewById(R.id.mei_tuan_list);
        mSpinner1 = (Spinner) findViewById(R.id.mei_tuan_spinner_one);
        mSpinner2 = (Spinner) findViewById(R.id.mei_tuan_spinner_two);
        mSpinner3 = (Spinner) findViewById(R.id.mei_tuan_spinner_three);
        mSpinner4 = (Spinner) findViewById(R.id.mei_tuan_spinner_four);
        ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.list_view, strings1);
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.list_view, strings2);
        ArrayAdapter adapter3 = new ArrayAdapter(this, R.layout.list_view, strings3);
        ArrayAdapter adapter4 = new ArrayAdapter(this, R.layout.list_view, strings4);
        mSpinner1.setAdapter(adapter1);
        mSpinner2.setAdapter(adapter2);
        mSpinner3.setAdapter(adapter3);
        mSpinner4.setAdapter(adapter4);
        inintData();
        DataGridViewAdapter adapter = new DataGridViewAdapter(this, list);
        mListView.setAdapter(adapter);
    }

    public void inintData() {
        addItem(R.mipmap.meituan_image7, R.mipmap.ic_label_nobooking, "港台美食荟", "【天府广场】雪花绵绵冰4选1，提供免费WiFi", changesText0ne("9.9"), changesTextTwo("20元"), "4.9分(68)");
        addItem(R.mipmap.meituan_image5, R.mipmap.ic_label_nobooking, "芭菲盛宴环球美食", "【孵化园】单人自助午餐，流年忘返美食", changesText0ne("118"), changesTextTwo("148元"), "4.5分(8054)");
        addItem(R.mipmap.meituan_image2, R.mipmap.ic_label_nobooking, "新石器烤肉", "【春熙路等】代金券1张，全场通用，可叠加使用", changesText0ne("88"), changesTextTwo("多优惠+"), "4.7分(24536)");
        addItem(R.mipmap.meituan_image1, R.mipmap.ic_label_nobooking, "水果捞", "【春熙路】水果捞特色饮品3选1，提供免费WiFi", changesText0ne("1"), changesTextTwo("28元"), "4.7分(57)");
        addItem(R.mipmap.meituan_image3, R.mipmap.ic_label_nobooking, "天府火锅", "【天府广场】特色自助火锅，5人以上8折优惠", changesText0ne("30"), changesTextTwo("48元"), "4.6分(2104)");
    }

    public void addItem(int i, int i1, String t, String c, SpannableString p, SpannableString p1, String a) {
        DataResIds mainIntentBean = new DataResIds(i, i1, t, c, p, p1, a);
        list.add(mainIntentBean);
    }

    public SpannableString changesText0ne(String s) {
        int n = s.length();
        SpannableString spannableString = new SpannableString(s + "元");
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.common_mei_tuan)),
                0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(25, true),
                0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),
                0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public SpannableString changesTextTwo(String s) {
        int n = s.length();
        SpannableString spannableString = new SpannableString(s);
        if (s.equals("多优惠+")) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.common_mei_tuan_p1)),
                    0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString.setSpan(new StrikethroughSpan(),
                    0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public class DataGridViewAdapter extends BaseAdapter {
        private List<DataResIds> list = new ArrayList();
        private LayoutInflater layoutInflater;

        public DataGridViewAdapter(Context context, List list) {
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
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.mei_tuan_view, null);
                ImageView iView = (ImageView) convertView.findViewById(R.id.mei_tuan_image_view);
                ImageView i1View = (ImageView) convertView.findViewById(R.id.mei_tuan_image1_view);
                TextView tView = (TextView) convertView.findViewById(R.id.mei_tuan_text_title);
                TextView cView = (TextView) convertView.findViewById(R.id.mei_tuan_text_content);
                TextView pView = (TextView) convertView.findViewById(R.id.mei_tuan_text_price);
                TextView p1View = (TextView) convertView.findViewById(R.id.mei_tuan_text_price1);
                TextView aView = (TextView) convertView.findViewById(R.id.mei_tuan_text_appraise);

                viewHolder = new ViewHolder();
                viewHolder.imageView = iView;
                viewHolder.imageView1 = i1View;
                viewHolder.title = tView;
                viewHolder.content = cView;
                viewHolder.price = pView;
                viewHolder.price1 = p1View;
                viewHolder.appraise = aView;
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            DataResIds item = (DataResIds) getItem(position);
            viewHolder.imageView.setImageResource(item.getImageView());
            viewHolder.imageView1.setImageResource(item.getImageView1());
            viewHolder.title.setText(item.getTitle());
            viewHolder.content.setText(item.getContent());
            viewHolder.price.setText(item.getPrice());
            viewHolder.price1.setText(item.getPrice1());
            viewHolder.appraise.setText(item.getAppraise());
            return convertView;
        }
    }

    public class ViewHolder {
        TextView title;
        TextView content;
        TextView appraise;
        ImageView imageView;
        ImageView imageView1;
        TextView price;
        TextView price1;
    }

    public class DataResIds {
        private String title, content, appraise;
        private int imageView, imageView1;
        private SpannableString price, price1;

        public DataResIds(int i, int i1, String t, String c, SpannableString p, SpannableString p1, String a) {
            title = t;
            content = c;
            price = p;
            price1 = p1;
            appraise = a;
            imageView = i;
            imageView1 = i1;
        }

        public SpannableString getPrice1() {
            return price1;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getAppraise() {
            return appraise;
        }

        public int getImageView() {
            return imageView;
        }

        public int getImageView1() {
            return imageView1;
        }

        public SpannableString getPrice() {
            return price;
        }
    }
}
