package com.example.administrator.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.zhangxsapplication.R;

public class NewsContentFragment extends Fragment {
    private static final String MESSAGE = "MESSAGE";
    private String message;
    public TextView mTextView;
    public static Fragment newInstance(String meg){
        NewsContentFragment newsContentFragment = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE,meg);
        newsContentFragment.setArguments(bundle);
        return newsContentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            message = bundle.getString(MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_content, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView = (TextView) getView().findViewById(R.id.fragment_new_text);
        mTextView.setText(message);
    }
}
