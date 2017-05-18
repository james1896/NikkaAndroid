package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.never.nikkaandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by toby on 18/04/2017.
 */

public class RecordListAdapter extends BaseListAdapter {

    public RecordListAdapter(Context context, ArrayList<Map> list) {
        super(context, list);

        if( list == null || list.size() == 0 ){
            ArrayList<Map> list1 = new ArrayList<>();

            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("name","XXX");
            map1.put("time","XXX");
            map1.put("price","XXX");
            list1.add(map1);
            this.dataList = list1;

        }else {
            this.dataList = list;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String,String> map = this.dataList.get(position);

        View item = mInflater.inflate(R.layout.home_record_listview_item, null);
        TextView title = (TextView) item.findViewById(R.id.record_item_name);
        TextView time = (TextView) item.findViewById(R.id.record_item_time);
        TextView money = (TextView) item.findViewById(R.id.record_item_money);

        title.setText(map.get("name"));
        time.setText(map.get("time"));
        money.setText(map.get("price"));
        return item;
    }
}
