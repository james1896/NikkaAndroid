package com.never.nikkaandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.TextView;

import com.never.nikkaandroid.R;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B dataBind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getContentView());
        dataBind = DataBindingUtil.setContentView(this, getContentView());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        init();
    }

    //colorInt <=0 使用默认透明颜色
    protected void setNavbar(String title, @ColorInt int color){
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tv = (TextView) findViewById(R.id.toolbar_title);
        Typeface type= Typeface.createFromAsset(getAssets(),"font/MarkerFelt.ttf");
        tv.setTypeface(type);
        tv.setText(title);

        if(color <= 0)
        toolbar.setBackgroundColor(color);
    }

    protected void backPress(){
        //back

    }

    protected abstract int getContentView();

    /**
     * 初始化操作
     */
    protected abstract void init();
}
