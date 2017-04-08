package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.never.nikkaandroid.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by toby on 08/04/2017.
 */

public class BaseListAdapter extends BaseAdapter {

    protected LayoutInflater mInflater = null;
    protected ArrayList<Map> dataList;

    public BaseListAdapter(Context context,ArrayList<Map> list){

        //根据context上下文加载布局，这里的是 本身，即this
        this.mInflater = LayoutInflater.from(context);
        this.dataList = list;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Map<String,Object> map = this.dataList.get(position);

        View item = mInflater.inflate(R.layout.me_listview_item, null);
        TextView title = (TextView)item.findViewById(R.id.ItemTitle);
        ImageView img = (ImageView)item.findViewById(R.id.imageView);

        String titleS = (String) map.get("title");
        int resid = (int) map.get("resId");

        Log.e("resId",""+resid);
        title.setText(titleS);

        img.setImageResource(resid);
        return item;
    }
}
