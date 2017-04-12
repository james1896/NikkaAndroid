package com.never.nikkaandroid.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.never.nikkaandroid.R;

/**
 * Created by toby on 12/04/2017.
 */

public class TransView extends LinearLayout implements Runnable, Animator.AnimatorListener {

    private Handler handler;
    private ObjectAnimator animator;
    private TextView textView;
    public TransView(Context context) {
        super(context);
        init(context);
    }

    public TransView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TransView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TransView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_trans_view, this);
        handler = new Handler();
    }


    public void startTrans(float height, int time){
        setVisibility(View.VISIBLE);

        animator = ObjectAnimator.ofFloat(this, "y", -height, 0f);
        animator.setDuration(time);
        animator.setAutoCancel(false);
        animator.addListener(this);

        animator.start();

    }

    public void setText(String s){
        textView.setText(s);
    }

    @Override
    public void run() {
        setVisibility(View.GONE);
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        handler.postDelayed(this, 2000);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
