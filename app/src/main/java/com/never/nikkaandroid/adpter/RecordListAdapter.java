package com.never.nikkaandroid.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

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
        return super.getView(position, convertView, parent);
    }
}
