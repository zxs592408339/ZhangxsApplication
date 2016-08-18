package com.example.administrator.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.zhangxsapplication.R;
import com.example.administrator.zhangxsapplication.WebViewActivity;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private OnMenuClickListener mOnMenuClickListener;
    private Button mButtonOne, mButtonTwo, mButtonThree, mButtonFour, mButtonFive;


    public interface OnMenuClickListener {
        void onMenuClick(View v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMenuClickListener) {
            this.mOnMenuClickListener = (OnMenuClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "必需实现接口：OnMenuClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButtonOne = (Button) getView().findViewById(R.id.menu_one_brn);
        mButtonTwo = (Button) getView().findViewById(R.id.menu_two_brn);
        mButtonThree = (Button) getView().findViewById(R.id.menu_three_brn);
        mButtonFour = (Button) getView().findViewById(R.id.menu_four_brn);
        mButtonFive = (Button) getView().findViewById(R.id.menu_five_brn);
        mButtonOne.setOnClickListener(this);
        mButtonTwo.setOnClickListener(this);
        mButtonThree.setOnClickListener(this);
        mButtonFour.setOnClickListener(this);
        mButtonFive.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mOnMenuClickListener != null) {
            switch (view.getId()) {
                case R.id.menu_one_brn:
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_two_brn:
                    break;
                case R.id.menu_three_brn:
                    break;
                case R.id.menu_four_brn:
                    break;
                case R.id.menu_five_brn:
                    break;
            }
            mOnMenuClickListener.onMenuClick(view);
        }
    }

    public static Fragment newInstance() {
        return new MenuFragment();
    }
}
