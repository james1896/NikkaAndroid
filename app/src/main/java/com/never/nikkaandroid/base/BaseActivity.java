package com.never.nikkaandroid.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        init();
    }

    protected abstract int getContentView();

    /**
     * 初始化操作
     */
    protected abstract void init();
}
