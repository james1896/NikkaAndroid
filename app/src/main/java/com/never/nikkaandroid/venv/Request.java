package com.never.nikkaandroid.venv;

import android.util.Log;

/**
 * Created by toby on 25/04/2017.
 */

public class Request {

    private static volatile Request request;
    public static synchronized Request getInstant(){
        if(request == null){
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (Request.class){
                if(request == null) {
                    request = new Request();
                }
            }
        }
        return request;
    }

    public Request login(){

        return request;
    }

    public Request login1(){

        return request;
    }

    public  void success(){

        Log.e("login.success","login.success");
    }
}
