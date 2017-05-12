package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.venv.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by toby on 20/04/2017.
 */

public class MeListAdapter extends BaseListAdapter {

    public MeListAdapter(Context context, ArrayList<Map> list) {
        super(context, list);

        this.dataList = getDataList();
    }

    public void setDataList(ArrayList<Map> list){
        this.dataList = getDataList();

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Map<String,Object> map = this.dataList.get(position);

        View item = mInflater.inflate(R.layout.me_listview_item, null);
        TextView title = (TextView)item.findViewById(R.id.ItemTitle);
        ImageView img = (ImageView)item.findViewById(R.id.imageView);

        String titleS = (String) map.get("title");
        int resid = (int) map.get("resId");
        title.setText(titleS);

        img.setImageResource(resid);
        return item;
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

        if(AppManager.getInstance().getLogin()){
            Map<String,Object> map6 = new HashMap<String,Object>();
            map6.put("title","退出账户");
            map6.put("resId",R.drawable.me_list_exit);
            list.add(map6);
        }
        return list;

    }
}
