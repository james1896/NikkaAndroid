package com.never.nikkaandroid.home;

import android.util.Log;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.never.nikkaandroid.R;
import com.never.nikkaandroid.adpter.RecordListAdapter;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.base.JsonParse;
import com.never.nikkaandroid.base.model.BaseModel;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

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
        final ListView listview = (ListView)findViewById(R.id.recordListView);


        RequestManager.getInstant().queryOrder(new RequestCallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                super.onSuccess(s, call, response);

                Log.e("queryOrder",s);
                BaseModel<List<RecordModel>> model = JsonParse.parser.fromJson(s, new TypeToken<BaseModel<List<RecordModel>> >(){}.getType());
               Log.e("0000", model.toString()+"|" +model.getStatusCode());

                ArrayList<Map> list = new ArrayList<>();
                for(RecordModel tmp:model.getData())
                {


                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("name",tmp.getName());
                    map.put("price",tmp.getPrice());
                    map.put("time",tmp.getTime());
                    list.add(map);
                }

                listview.setAdapter(new RecordListAdapter(RecordActivity.this,list));
            }
        });
    }

}
