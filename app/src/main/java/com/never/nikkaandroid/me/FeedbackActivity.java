package com.never.nikkaandroid.me;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseActivity;

public class FeedbackActivity extends BaseActivity {



    @Override
    protected int getContentView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void init() {

        setNavbar("FeedBack",getResources().getColor(R.color.theme_pink));
    }
}
