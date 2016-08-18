package com.example.administrator.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.zhangxsapplication.R;

public class MyAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mAnimationTxt;
    private Button mAlphaBrn, mRotationBrn, mTranslationXBrn, mScaleYBrn, mMixAnimBrn, mXmlMixAnimBrn;
    boolean flag = true;
    boolean flag1 = true;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animation);
        mAnimationTxt = (TextView) findViewById(R.id.animation_text);
        mAlphaBrn = (Button) findViewById(R.id.animation_alpha_brn);
        mRotationBrn = (Button) findViewById(R.id.animation_rotation_brn);
        mTranslationXBrn = (Button) findViewById(R.id.animation_translationX_brn);
        mScaleYBrn = (Button) findViewById(R.id.animation_scaleY_brn);
        mMixAnimBrn = (Button) findViewById(R.id.animation_mix_brn);
        mXmlMixAnimBrn = (Button) findViewById(R.id.animation_xml_brn);
        mAlphaBrn.setOnClickListener(this);
        mRotationBrn.setOnClickListener(this);
        mTranslationXBrn.setOnClickListener(this);
        mScaleYBrn.setOnClickListener(this);
        mMixAnimBrn.setOnClickListener(this);
        mXmlMixAnimBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animation_alpha_brn:
                alpha();
                break;
            case R.id.animation_rotation_brn:
                rotation();
                break;
            case R.id.animation_translationX_brn:
                translationX();
                break;
            case R.id.animation_scaleY_brn:
                scaleY();
                break;
            case R.id.animation_mix_brn:
                mixAnim();
                break;
            case R.id.animation_xml_brn:
                xmlMixAnim();
                break;
        }
    }

    // TODO: 2016/8/9 淡入淡出
    public void alpha() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimationTxt, "alpha", 1f, 0f, 1f);
        animator.setDuration(3000);
        animator.start();
    }

    // TODO: 2016/8/9 旋转
    public void rotation() {
        if (flag) {
            objectAnimator = ObjectAnimator.ofFloat(mAnimationTxt, "rotation", 0, 360f);
            objectAnimator.setDuration(3000);
            objectAnimator.setInterpolator(new LinearInterpolator());//不停顿
            objectAnimator.setRepeatCount(-1);//设置动画重复次数
            objectAnimator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式、
            objectAnimator.start();
            flag = false;
        } else {
            if (flag1) {
                objectAnimator.pause();
                flag1 = false;
            } else {
                objectAnimator.resume();
                flag1 = true;
            }
        }
    }

    // TODO: 2016/8/9 平移
    public void translationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimationTxt, "translationX", -500f, 0f);
        animator.setDuration(3000);
        animator.start();
    }

    // TODO: 2016/8/9 放缩
    public void scaleY() {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mAnimationTxt, "scaleY", 1f, 5f, 1f);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mAnimationTxt, "scaleX", 1f, 5f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animatorY).with(animatorX);
        animSet.setDuration(10000);
        animSet.start();
    }

    // TODO: 2016/8/9 混合动画
    public void mixAnim() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mAnimationTxt, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mAnimationTxt, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mAnimationTxt, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(3000);
        animSet.start();
    }

    // TODO: 2016/8/9 xml动画
    public void xmlMixAnim() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.anim_file);
        animator.setTarget(mAnimationTxt);
        animator.start();
    }

}
