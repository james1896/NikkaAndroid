package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return null;
    }


}
