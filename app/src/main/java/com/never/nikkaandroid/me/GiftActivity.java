package com.never.nikkaandroid.me;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.adpter.GiftAdapter;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.databinding.ActivityGiftBinding;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.CommonUtils;
import com.never.nikkaandroid.views.TransView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GiftActivity extends BaseActivity<ActivityGiftBinding> implements GiftInterface {

    private ViewGroup layout;
    @Override
    protected int getContentView() {
        return R.layout.activity_gift;
    }

    @Override
    protected void init() {
        //toolbar
        setNavbar("Gift To",getResources().getColor(R.color.theme_pink));

        this.layout = (ViewGroup) findViewById(R.id.gift_activity_layout);


        ListView listView = (ListView) findViewById(R.id.gift_listview);
        listView.setAdapter(new GiftAdapter(this,getDataList()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            String avaPoints = "可用积分："+AppManager.getInstance().getPoints();
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        GiftDialogFragment giftDialog = new GiftDialogFragment("好友用户名",avaPoints,"赠送的积分数");
                        giftDialog.setGiftInterface(GiftActivity.this);
                        giftDialog.show(getSupportFragmentManager(),"ss");
                        break;
                    }
                    case 1:{
                        GiftDialogFragment giftDialog = new GiftDialogFragment("好友用户名",avaPoints,"赠送的几个苹果？");
                        giftDialog.show(getSupportFragmentManager(),"ss");
                        break;
                    }
                }
            }
        });
    }

    private ArrayList<Map> getDataList(){
        ArrayList<Map> list = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("title","积分转赠");
//        map1.put("resId",R.drawable.me_list_zengsong);
        list.add(map1);

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("title","苹果赠送");
//        map2.put("resId",R.drawable.me_list_key);
        list.add(map2);

        return list;

    }

    @Override
    public void inputLoginInforCompleted() {

        this.layout.post(new Runnable() {
            @Override
            public void run() {
                TransView view = new TransView(GiftActivity.this);
                view.setText("转赠积分给好友成功，赶快告诉你的小伙伴吧");
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) CommonUtils.getWindowWidth(GiftActivity.this),
                        (int)getResources().getDimension(R.dimen.m_80));
                GiftActivity.this.layout.addView(view, params);
                view.startTrans(getResources().getDimension(R.dimen.m_100), 1000);
            }
        });

    }
}
