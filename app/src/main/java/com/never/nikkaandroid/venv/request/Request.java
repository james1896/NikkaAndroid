package com.never.nikkaandroid.venv.request;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by toby on 25/04/2017.
 */

public class Request {

  public static void POST(final String url, Map<String, String> params,RequestCallBack callback){
      OkGo.post(url)    // 请求方式和请求url, get请求不需要拼接参数，支持get，post，put，delete，head，options请求
//              .tag(this)               // 请求的 tag, 主要用于取消对应的请求
              .isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
              .connTimeOut(10000)      // 设置当前请求的连接超时时间
              .readTimeOut(10000)      // 设置当前请求的读取超时时间
              .writeTimeOut(10000)     // 设置当前请求的写入超时时间
              .cacheKey("cacheKey")    // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
              .cacheTime(5000)         // 缓存的过期时间,单位毫秒
              .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST) // 缓存模式，详细请看第四部分，缓存介绍
              .headers("header1", "headerValue1")     		// 添加请求头参数
              .headers("header2", "headerValue2")     		// 支持多请求头参数同时添加
              .params(params)
              .execute(callback);

  }

    public static void GET(){


    }

    //json转map
    public static Map<String, Object> getMapForJson(String jsonStr){
        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter= jsonObject.keys();
            String key;
            Object value ;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return null;
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
