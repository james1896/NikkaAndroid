package com.never.nikkaandroid.venv.request;

import android.util.Log;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;
import com.never.nikkaandroid.NikkaApplication;
import com.never.nikkaandroid.venv.CommonUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by toby on 09/05/2017.
 */

public abstract class  RequestCallBack extends StringCallback{

    private String url;
    private HttpParams params;

    @Override
    public String convertSuccess(Response response) throws Exception {
        return super.convertSuccess(response);
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        Log.e("POST","输入url:\n" + this.url + "\n输入参数:" + this.params + "\n错误原因:" + e);
    }

    @Override
    public void onSuccess(String s, Call call, Response response) {

        Log.e("POST","输入url:\n" + this.url + "\n输入参数:" + this.params + "\n输出参数:" + s);
    }

    @Override
    public void onAfter(String s, Exception e) {
        super.onAfter(s, e);

        CommonUtils.saveLong(NikkaApplication.getContext(),this.url,CommonUtils.getCurrentDate());
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        this.url = request.getUrl();
        this.params = request.getParams();

    }
}
