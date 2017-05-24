package com.never.nikkaandroid.venv.request;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.never.nikkaandroid.NikkaApplication;
import com.never.nikkaandroid.venv.CommonUtils;

import java.util.Map;

/**
 * Created by toby on 25/04/2017.
 */

public class Request {

    protected int refreshTime;
    protected Boolean isRequest;
    public void POST(final String url, Map<String, String> params,RequestCallBack callback){


//        Log.e("refreshRate",this.currentInterfaceName);

        long refreshRate = CommonUtils.getLong(NikkaApplication.getContext(),url);
        if(refreshRate > 0){
            long currentDate = CommonUtils.getCurrentDate();
            if(currentDate - refreshRate < refreshTime){
                //接口请求频率高，放弃请求
                this.isRequest = false;
            }else {
                this.isRequest = true;
            }
        }

        if(!isRequest){
            Log.e("POST","检查到 "+url+" 接口刷新频率过高，放弃本次请求");
            return;
        }
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

    public void GET(){

    }

}
