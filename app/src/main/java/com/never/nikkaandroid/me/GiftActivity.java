package com.never.nikkaandroid.me;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.databinding.ActivityGiftBinding;


public class GiftActivity extends BaseActivity<ActivityGiftBinding> {

    @Override
    protected int getContentView() {
        return R.layout.activity_gift;
    }

    @Override
    protected void init() {
        //toolbar
        setNavbar("Gift To",getResources().getColor(R.color.theme_pink));
    }

}
