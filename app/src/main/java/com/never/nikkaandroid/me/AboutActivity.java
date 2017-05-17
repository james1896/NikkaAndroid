package com.never.nikkaandroid.me;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseActivity;

/**
 * Created by toby on 17/05/2017.
 */

public class AboutActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {

        setNavbar("About Us",getResources().getColor(R.color.theme_pink));
    }
}
