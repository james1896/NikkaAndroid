package com.never.nikkaandroid.venv;

/**
 * Created by toby on 25/04/2017.
 */

public class Request {

    public  class Login{

        public Login(String userName,String pwd){


        }
    }
//    public static class loginReq{
//
//        private static volatile loginReq req;
//        public static synchronized loginReq getInstant(){
//            if(req == null){
//                //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
//                synchronized (loginReq.class){
//                    if(req == null) {
//                        req = new loginReq();
//                    }
//                }
//            }
//            return req;
//        }
//
//        public loginReq login(){
//
//            return req;
//        }
//
//        public  void success(){
//
//            Log.e("login.success","login.success");
//        }
//    }

}
