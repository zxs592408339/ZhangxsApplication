package com.example.administrator.charting.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.zhangxsapplication.R;

public class MyTextView extends View {
    private String mText;
    private int mTextColor;
    private int mBackground;
    private float mTextSize;
    private Paint paint;
    private Rect rect;

    public MyTextView(Context context) {
        super(context);
        inint(context, null, 0);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inint(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint(context, attrs, defStyleAttr);
    }

    public void inint(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray t = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, 0, 0);
        try {
            mText = t.getString(R.styleable.MyTextView_text);
            mTextColor = t.getColor(R.styleable.MyTextView_textcolor, Color.BLACK);
            mTextSize = t.getDimension(R.styleable.MyTextView_textsize, 16);
            mBackground = t.getColor(R.styleable.MyTextView_background,Color.WHITE);
        } finally {
            t.recycle();
        }

        paint = new Paint();
        paint.setTextSize(mTextSize);

        rect = new Rect();
        paint.getTextBounds(mText, 0, mText.length(), rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:  //设置了明确的值或者是MATCH_PARENT
                width = specSize + getPaddingLeft() + getPaddingRight();
                break;
            case MeasureSpec.AT_MOST:  //WARP_CONTENT
                width = rect.width() + getPaddingLeft() + getPaddingRight()+10;
                break;
            case MeasureSpec.UNSPECIFIED:  //子布局想要多大就多大，很少使用
                break;
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:  //设置了明确的值或者是MATCH_PARENT
                height = specSize + getPaddingBottom() + getPaddingTop();
                break;
            case MeasureSpec.AT_MOST:  //WARP_CONTENT
                height = rect.height() + getPaddingBottom() + getPaddingTop();
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(mBackground);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(mTextColor);
        canvas.drawText(mText, getWidth() / 2 - rect.width() / 2, (getHeight() / 2 + rect.height() / 2) - 15, paint);
    }
}
