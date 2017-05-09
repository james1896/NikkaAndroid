package com.never.nikkaandroid.venv.request;

import android.util.Log;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by toby on 09/05/2017.
 */

public abstract class  RequestCallBack extends StringCallback{

    @Override
    public String convertSuccess(Response response) throws Exception {
        return super.convertSuccess(response);
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
    }

    @Override
    public void onSuccess(String s, Call call, Response response) {

    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        Log.e("POST","输入url:\n" + request.getUrl() + "\n输入参数:" + request.getParams());
    }
}
