package com.never.nikkaandroid.unitTest;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.never.nikkaandroid.base.JsonParse;
import com.never.nikkaandroid.base.login.LoginModel;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by toby on 12/05/2017.
 */

public class TBUnitTest {
    public static void unitTest(){
        TBUnitTest.login();
    }
    private static void login(){
        RequestManager.getInstant().login("hanks", "123456", new RequestCallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                super.onSuccess(s, call, response);
                LoginModel model = JsonParse.parser.fromJson(s, new TypeToken<LoginModel>(){}.getType());

                Log.e("login",model.toString());
                if(model.getStatus() != -1) {
                    AppManager.getInstance().setPoints(model.getData().getPoints());
                    AppManager.getInstance().setUser_id(model.getUser_id());
                    Log.e("data", model.getData().getPoints() + "&&&&&&&" + model.getUser_id());
                }
            }
        });
    }
}
