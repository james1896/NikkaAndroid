package com.never.nikkaandroid.me;

import android.view.View;
import android.widget.ImageView;

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
        setNavbar("Gift To",getResources().getColor(R.color.theme_pink));

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
