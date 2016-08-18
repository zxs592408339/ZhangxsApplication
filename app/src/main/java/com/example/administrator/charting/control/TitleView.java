package com.example.administrator.charting.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhangxsapplication.R;

public class TitleView extends RelativeLayout implements View.OnClickListener {
    private ImageView backImgView;
    private TextView titleView, moreView;
    private String title, more;
    private int backImg;
    private OnTitleClickListener onTitleClickListener;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public interface OnTitleClickListener {
        void onTitleClick(View view);
    }

    public TitleView(Context context) {
        super(context);
        inInt(context, null, 0);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inInt(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inInt(context, attrs, defStyleAttr);
    }

    public void inInt(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray t = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleView, 0, 0);
        try {
            title = t.getString(R.styleable.TitleView_titletext);
            more = t.getString(R.styleable.TitleView_moretext);
            backImg = t.getResourceId(R.styleable.TitleView_backsrc, 0);
        } finally {
            t.recycle();
        }
        setMyView();
    }

    public void setMyView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.my_linear_relative_view, this, true);
        backImgView = (ImageView) view.findViewById(R.id.my_img_view_back);
        titleView = (TextView) view.findViewById(R.id.my_texe_view_title);
        moreView = (TextView) view.findViewById(R.id.my_text_view_more);

        if (backImg != 0)
            backImgView.setImageResource(backImg);
        if (title != null)
            titleView.setText(title);
        if (more != null)
            moreView.setText(more);

        backImgView.setOnClickListener(this);
        moreView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onTitleClickListener != null) {
            switch (view.getId()) {
                case R.id.my_img_view_back:
                    onTitleClickListener.onTitleClick(view);
                    break;
                case R.id.my_text_view_more:
                    onTitleClickListener.onTitleClick(view);
                    break;
            }
        }
    }
}
