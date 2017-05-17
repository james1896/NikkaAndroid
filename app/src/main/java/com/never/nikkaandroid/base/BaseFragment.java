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

    // 标志位，标志已经初始化完成。
    private boolean isPrepared,isVisible,isLazyLoad;

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
        this.isPrepared = true;
        onVisible();
        return dataBind.getRoot();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    private void onVisible() {
        if (!isPrepared || !isVisible) {
            return;
        }
        viewWillAppear();
        if (isLazyLoad) return;
        isLazyLoad = true;
        lazyLoad();
    }
    /**
     * 界面不可见时自动调用
     */
    private void onInvisible() {
        viewWillDisappear();
    }

    protected void viewWillAppear(){

    }

    protected void viewWillDisappear(){

    }
    /**
     * fragment处于可见状态时自动调用该方法，实现懒加载，一般用作网络请求
     */
    protected  void lazyLoad(){

    };


    public abstract int getContentView();

    public abstract void init();
}
