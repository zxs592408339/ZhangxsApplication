package com.example.administrator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.zhangxsapplication.R;

public class NewsListFragment extends Fragment implements AdapterView.OnItemClickListener {
    public ListView mNewListView;
    String[] news = {"新闻1", "新闻2", "新闻3", "新闻4", "新闻5"};
    private OnNewsItemListener mNewsItemListener;

    public static Fragment newInstance(){
        return new NewsListFragment();
    }

    public interface OnNewsItemListener {
        void onItemClick(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewsItemListener) {
            this.mNewsItemListener = (OnNewsItemListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "必需实现接口：OnNewsItemListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewListView = (ListView) getView().findViewById(R.id.fragment_list);
        mNewListView.setOnItemClickListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, news);
        mNewListView.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayAdapter arrayAdapter = (ArrayAdapter) adapterView.getAdapter();
        String item = (String) arrayAdapter.getItem(i);
        if(mNewsItemListener != null){
            mNewsItemListener.onItemClick(item);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNewsItemListener = null;
    }
}
