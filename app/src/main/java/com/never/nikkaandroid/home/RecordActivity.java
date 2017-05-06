package com.never.nikkaandroid.home;

import android.widget.ListView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.adpter.RecordListAdapter;
import com.never.nikkaandroid.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by toby on 18/04/2017.
 */

public class RecordActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_record;
    }

    @Override
    protected void init() {
        ListView listview = (ListView)findViewById(R.id.recordListView);
        listview.setAdapter(new RecordListAdapter(this,getDataList()));
    }

    private ArrayList<Map> getDataList(){
        ArrayList<Map> list = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("title","礼物赠送");
        map1.put("resId",R.drawable.me_list_zengsong);
        list.add(map1);

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("title","生活助手");
        map2.put("resId",R.drawable.me_list_key);
        list.add(map2);

        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("title","意见反馈");
        map3.put("resId",R.drawable.me_list_yijian);
        list.add(map3);

        Map<String,Object> map4 = new HashMap<String,Object>();
        map4.put("title","呜谢组织");
        map4.put("resId",R.drawable.me_list_thank);
        list.add(map4);

        Map<String,Object> map5 = new HashMap<String,Object>();
        map5.put("title","关于我们");
        map5.put("resId",R.drawable.me_list_about);
        list.add(map5);
        return list;

    }
}
