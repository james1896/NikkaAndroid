package com.never.nikkaandroid;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.never.nikkaandroid.adpter.MainActivityAdpter;
import com.never.nikkaandroid.base.BaseActivity;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{

    private TextView titleTextView;
    private String[] titles = new String[]{"Nikka","","Profile"};
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏1
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        titleTextView = (TextView) findViewById(R.id.toolbar_title);
        titleTextView.setText(titles[0]);
        Typeface type= Typeface.createFromAsset(getAssets(),"font/MarkerFelt.ttf");
        titleTextView.setTypeface(type);

        //viewPage
        ViewPager viewPage = (ViewPager) findViewById(R.id.viewpage);
        viewPage.setAdapter(new MainActivityAdpter(getSupportFragmentManager()));

        //tablayout
        android.support.design.widget.TabLayout tabLayout = (android.support.design.widget.TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        titleTextView.setText(titles[tab.getPosition()]);

        switch (tab.getPosition()){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            default:
                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
