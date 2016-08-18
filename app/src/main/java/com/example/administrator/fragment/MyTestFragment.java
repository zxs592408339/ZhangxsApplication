package com.example.administrator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dem.L;
import com.example.administrator.zhangxsapplication.R;

public class MyTestFragment extends Fragment {


    public MyTestFragment() {
        // Required empty public constructor
        L.e("LifeFragment() 构造方法");
    }

    /**
     * 绑定Fragment
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.e("LifeFragment onAttach  >>>>");
    }

    /**
     * 创建Fragment
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e("LifeFragment onCreate  >>>>");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        L.e("LifeFragment onCreateView  >>>>");

        return  inflater.inflate(R.layout.fragment_my_test, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.e("LifeFragment onActivityCreated  >>>>");
    }

    @Override
    public void onStart() {
        super.onStart();
        L.e("LifeFragment onStart  >>>>");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e("LifeFragment onResume  >>>>");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.e("LifeFragment onPause  >>>>");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.e("LifeFragment onStop  >>>>");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.e("LifeFragment onDestroyView  >>>>");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e("LifeFragment onDestroy  >>>>");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.e("LifeFragment onDetach  >>>>");
    }
}
