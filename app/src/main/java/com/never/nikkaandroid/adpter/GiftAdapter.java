package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.never.nikkaandroid.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by toby on 06/05/2017.
 */

public class GiftAdapter extends BaseListAdapter {
    public GiftAdapter(Context context, ArrayList<Map> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Map<String,Object> map = this.dataList.get(position);

        View item = mInflater.inflate(R.layout.gift_listview_item, null);
        TextView title = (TextView)item.findViewById(R.id.gift_item_textView);
//        ImageView img = (ImageView)item.findViewById(R.id.imageView);

        String titleS = (String) map.get("title");
//        int resid = (int) map.get("resId");

//        Log.e("resId",""+resid);
        title.setText(titleS);

//        img.setImageResource(resid);
        return item;
    }
}
