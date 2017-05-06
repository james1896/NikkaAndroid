package com.never.nikkaandroid.me;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseActivity;

public class ThankActivity extends BaseActivity {



    @Override
    protected int getContentView() {
        return R.layout.activity_thank;
    }

    @Override
    protected void init() {
        setNavbar("Thank For",getResources().getColor(R.color.theme_pink));
    }
}
