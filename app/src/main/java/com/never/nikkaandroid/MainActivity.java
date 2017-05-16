package com.never.nikkaandroid;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RelativeLayout;

import com.never.nikkaandroid.adpter.MainActivityAdpter;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.base.view.TabLayoutItemView;
import com.never.nikkaandroid.databinding.ActivityMainBinding;
import com.never.nikkaandroid.venv.CommonUtils;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;
import com.never.nikkaandroid.views.TransView;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements TabLayout.OnTabSelectedListener{

    private String[] titles = new String[]{"Nikka","Grid","Profile"};

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void init() {

        //如果超过24小时 就收集userInfo
        if(!CommonUtils.isIntraday(this)){
            RequestManager.getInstant().userinfo(CommonUtils.getUniquePsuedoID(), CommonUtils.collectDeviceInfo(this), new RequestCallBack() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    super.onSuccess(s, call, response);
                    Log.e("userinfo",s);

                    CommonUtils.saveLong(MainActivity.this,CommonUtils.collection_userinfo_lasttime,CommonUtils.getCurrentDate());
                }
            });
        }

//        TBUnitTest.unitTest();
        //toolbar
        setNavbar(titles[0],0);
        //view动画
//        layout = (RelativeLayout) findViewById(R.id.layout);
        dataBind.layout.post(new Runnable() {
            @Override
            public void run() {
                TransView view = new TransView(MainActivity.this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) CommonUtils.getWindowWidth(MainActivity.this),
                        (int)getResources().getDimension(R.dimen.m_80));
                dataBind.layout.addView(view, params);
                view.startTrans(getResources().getDimension(R.dimen.m_100), 1000);
            }
        });

        //viewPage
        ViewPager viewPage = (ViewPager) findViewById(R.id.viewpage);
        viewPage.setOffscreenPageLimit(3);
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
        setNavbar(titles[tab.getPosition()],0);
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


