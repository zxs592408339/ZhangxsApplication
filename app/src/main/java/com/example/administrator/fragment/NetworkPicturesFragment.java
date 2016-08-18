package com.example.administrator.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zhangxsapplication.R;

public class NetworkPicturesFragment extends Fragment {
    private static final String PAGE_NO = "page_no";
    private static final String IMAGE_URL = "image_url";
    private static final String TOTAL_TAB = "total_tab";
    private ImageView mImageView;
    private TextView mPageNo, mTotalTab;
    public String imageUrl;
    private int pageNo;
    private int totalTab;

    public static Fragment newInstance(String imageUrls, int position, int length) {
        NetworkPicturesFragment networkPicturesFragment = new NetworkPicturesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrls);
        bundle.putInt(PAGE_NO, position);
        bundle.putInt(TOTAL_TAB, length);
        networkPicturesFragment.setArguments(bundle);
        return networkPicturesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getArguments() == null ? null : getArguments().getString(IMAGE_URL);
        pageNo = getArguments() == null ? -1 : getArguments().getInt(PAGE_NO);
        totalTab = getArguments() == null ? -1 : getArguments().getInt(TOTAL_TAB);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_network_pictures, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageView = (ImageView) getView().findViewById(R.id.network_img);
        mPageNo = (TextView) getView().findViewById(R.id.fragment_current_tab_txt);
        mTotalTab = (TextView) getView().findViewById(R.id.fragment_total_tab_txt);
        Glide.with(NetworkPicturesFragment.this).load(imageUrl).into(mImageView);
        mPageNo.setText("" + (pageNo + 1));
        mTotalTab.setText("" + totalTab);
    }
}
