package com.never.nikkaandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    protected T dataBind;
//    protected View contentView;
    public BaseFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dataBind = DataBindingUtil.inflate(inflater,getContentView(),container,false);
//        contentView = inflater.inflate(getContentView(), container, false);
        init();
//        return contentView;
        return dataBind.getRoot();
    }

    public abstract int getContentView();

    public abstract void init();
}
