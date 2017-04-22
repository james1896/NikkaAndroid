package com.never.nikkaandroid;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.never.nikkaandroid.adpter.MainActivityAdpter;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.base.CommonUtils;
import com.never.nikkaandroid.base.TabLayoutItemView;
import com.never.nikkaandroid.views.TransView;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{


    private RelativeLayout layout;
    private TextView titleTextView;
    private String[] titles = new String[]{"Nikka","","Profile"};
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {


//        Log.e("WIFI","Product Model: " + android.os.Build.BRAND + ","
//                + android.os.Build.VERSION.SDK + ","
//                + android.os.Build.VERSION.RELEASE);
        Log.e("Device",CommonUtils.collectDeviceInfo(this));

        //view动画
        layout = (RelativeLayout) findViewById(R.id.layout);
        layout.post(new Runnable() {
            @Override
            public void run() {
                TransView view = new TransView(MainActivity.this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(CommonUtils.getWindowWidth(MainActivity.this),
                        (int)getResources().getDimension(R.dimen.m_100));
                layout.addView(view, params);
                view.startTrans(getResources().getDimension(R.dimen.m_100), 1000);
            }
        });
        //toolbar title
        titleTextView = (TextView) findViewById(R.id.toolbar_title);
        titleTextView.setText(titles[0]);
        Typeface type= Typeface.createFromAsset(getAssets(),"font/MarkerFelt.ttf");
        titleTextView.setTypeface(type);

        //viewPage
        ViewPager viewPage = (ViewPager) findViewById(R.id.viewpage);
        viewPage.setAdapter(new MainActivityAdpter(this));

        //tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.getTabAt(0).setCustomView(new TabLayoutItemView(this,"水果",R.drawable.tab_home_icon_selector));
        tabLayout.getTabAt(1).setCustomView(new TabLayoutItemView(this,"格铺",R.drawable.tab_grid_icon_selector));
        tabLayout.getTabAt(2).setCustomView(new TabLayoutItemView(this,"我的",R.drawable.tab_me_icon_selector));
//        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.grape_normal));
//        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.activity_normal));
//        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.me_normal));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        titleTextView.setText(titles[tab.getPosition()]);
//        switch (tab.getPosition()){
//            case 0:
//                tab.setIcon(getResources().getDrawable(R.drawable.grape_press));
//                break;
//            case 1:
//                tab.setIcon(getResources().getDrawable(R.drawable.activity_press));
//                break;
//            case 2:
//                tab.setIcon(getResources().getDrawable(R.drawable.me_press));
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
//        switch (tab.getPosition()){
//            case 0:
//                tab.setIcon(getResources().getDrawable(R.drawable.grape_normal));
//                break;
//            case 1:
//                tab.setIcon(getResources().getDrawable(R.drawable.activity_normal));
//                break;
//            case 2:
//                tab.setIcon(getResources().getDrawable(R.drawable.activity_normal));
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}

                //android 6.0以上需要动态申请权限

//      Acp.getInstance(MainActivity.this).request(new AcpOptions.Builder()
//              .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
//              , Manifest.permission.READ_PHONE_STATE
//              , Manifest.permission.SEND_SMS)
////                以下为自定义提示语、按钮文字
//              .setDeniedMessage("Message")
//              .setDeniedCloseBtn("关闭")
//              .setDeniedSettingBtn("设置")
//              .setRationalMessage("rational")
//              .setRationalBtn("合理")
//              .build(),
//              new AcpListener() {
//@Override
//public void onGranted() {
//        writeSD();
//        getIMEI();
//        }
//
//@Override
//public void onDenied(List<String> permissions) {
//        Toast.makeText(MainActivity.this,permissions.toString() + "权限拒绝",Toast.LENGTH_SHORT).show();
//        }
//        });


