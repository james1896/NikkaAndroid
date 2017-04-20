package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.never.nikkaandroid.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by toby on 18/04/2017.
 */

public class RecordListAdapter extends BaseListAdapter {

    public RecordListAdapter(Context context, ArrayList<Map> list) {
        super(context, list);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String,Object> map = this.dataList.get(position);

        View item = mInflater.inflate(R.layout.home_record_listview_item, null);
        TextView title = (TextView) item.findViewById(R.id.textView);
        title.setText("test");
        return item;
    }
}
