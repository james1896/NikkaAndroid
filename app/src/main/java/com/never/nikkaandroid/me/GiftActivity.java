package com.never.nikkaandroid.me;

import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.databinding.ActivityGiftBinding;


public class GiftActivity extends BaseActivity<ActivityGiftBinding> implements View.OnClickListener{

    @Override
    protected int getContentView() {
        return R.layout.activity_gift;
    }

    @Override
    protected void init() {

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tv = (TextView) findViewById(R.id.toolbar_title);
        Typeface type= Typeface.createFromAsset(getAssets(),"font/MarkerFelt.ttf");
        tv.setTypeface(type);
        tv.setText("Gift To");
        toolbar.setBackgroundColor(getResources().getColor(R.color.me_headerview));

        //back
        ImageView iv = dataBind.giftBack;
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.gift_back:{
                finish();
                break;
            }

            default:
                break;
        }
    }
}
