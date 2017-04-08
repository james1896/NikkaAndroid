package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.never.nikkaandroid.R;

/**
 * Created by toby on 08/04/2017.
 */

public class BaseListAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;

    public BaseListAdapter(Context context){

        //根据context上下文加载布局，这里的是 本身，即this
        this.mInflater = LayoutInflater.from(context); ;
    }
    @Override
    public int getCount() {
        return 10;
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
        View item = mInflater.inflate(R.layout.me_listview_item, null);
        TextView title = (TextView)item.findViewById(R.id.ItemTitle);
        title.setText("hfdjsahljfd");
        return item;
    }
}
