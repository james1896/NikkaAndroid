package com.never.nikkaandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.never.nikkaandroid.R;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B dataBind;
    Toolbar toolbar;
    public  TextView navTitle;
    public TextView navSpaceLab;
    public TextView navSubtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getContentView());
        dataBind = DataBindingUtil.setContentView(this, getContentView());

        initStatusBar();
        initBaseView();

        init();
    }

    private void initStatusBar(){
        //沉浸式statusBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    private void initBaseView(){
        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            // setSupportActionBar(toolbar);
            Log.e("----->", toolbar.toString());
        }

        navTitle = (TextView) findViewById(R.id.toolbar_title);
        navSpaceLab = (TextView) findViewById(R.id.toolbar_space);
        navSubtitle = (TextView) findViewById(R.id.toolbar_subtitle);

        if(navTitle != null){
            Typeface type= Typeface.createFromAsset(getAssets(),"font/MarkerFelt.ttf");
            navTitle.setTypeface(type);
        }

        //back_white button
        ImageView back = (ImageView)findViewById(R.id.back);
        if(back != null){
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
    //colorInt =0 使用默认透明颜色
    protected void setNavbar(String title, @ColorInt int color){
        navTitle.setText(title);
        toolbar.setBackgroundColor(color);

    }

    protected void backPress(){
        //back_white

    }

    protected abstract int getContentView();

    /**
     * 初始化操作
     */
    protected abstract void init();
}
