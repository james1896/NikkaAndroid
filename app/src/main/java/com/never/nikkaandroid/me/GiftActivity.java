package com.never.nikkaandroid.me;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.adpter.GiftAdapter;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.databinding.ActivityGiftBinding;
import com.never.nikkaandroid.venv.GiftDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GiftActivity extends BaseActivity<ActivityGiftBinding> {

//    // 模拟数据
//    private List<String> dataList = null;
    @Override
    protected int getContentView() {
        return R.layout.activity_gift;
    }

    @Override
    protected void init() {
        //toolbar
        setNavbar("Gift To",getResources().getColor(R.color.theme_pink));

        ListView listView = (ListView) findViewById(R.id.gift_listview);
        listView.setAdapter(new GiftAdapter(this,getDataList()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        GiftDialogFragment giftDialog = new GiftDialogFragment();
                        giftDialog.show(getSupportFragmentManager(),"ss");
                        break;
                    }
                    case 1:{
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

}
