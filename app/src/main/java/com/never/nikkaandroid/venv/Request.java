package com.never.nikkaandroid.venv;

/**
 * Created by toby on 25/04/2017.
 */

public class Request {

    private static volatile Request request;
    public static synchronized Request getInstant(){
        if(request == null){
            synchronized (request){
                request = new Request();
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

    public  Request success(){

        return request;
    }
}
