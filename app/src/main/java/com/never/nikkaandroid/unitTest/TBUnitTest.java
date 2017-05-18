package com.never.nikkaandroid.unitTest;

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
//                LoginModel model = JsonParse.parser.fromJson(s, new TypeToken<LoginModel>(){}.getType());
//
//                Log.e("login",model.toString());
//                if(model.getStatusCode() == 1001){
//                    AppManager.getInstance().setPoints(model.getData().getPoints());
//                    Log.e("data",model.getData().getPoints() + "&&&&&&&"+ model.getData().getUser_id());
//                    AppManager.getInstance().setUser_id(model.getData().getUser_id());
//                }
            }
        });
    }
}
