package com.never.nikkaandroid.home;


import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;

import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements OnClickListener{


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
//        Intent intent = new Intent(getActivity(),BalanceActivity.class);
//        startActivity(intent);

        View v1 = contentView.findViewById(R.id.payLayout);
        v1.setOnClickListener(this);

        View v2 = contentView.findViewById(R.id.youhuiLayout);
        v2.setOnClickListener(this);

        View v3 = contentView.findViewById(R.id.recordLayout);
        v3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.payLayout:{
                Intent intent = new Intent(getActivity(),BalanceActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            case R.id.youhuiLayout:{
                Log.e("request","okgo");
                OkGo.get("http://10.66.67.81:8001/client/test1")     // 请求方式和请求url
                        .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                        .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                        .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, okhttp3.Call call, Response response) {
                                Log.e("okgo",s);
                            }
                        });
                break;
            }
            case R.id.recordLayout:{
                break;
            }
            default:
                break;
        }
    }
}
