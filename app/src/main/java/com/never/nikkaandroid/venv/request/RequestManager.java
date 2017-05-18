package com.never.nikkaandroid.venv.request;

import android.util.Log;

import com.never.nikkaandroid.base.JniHello;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.RSA;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by toby on 08/05/2017.
 */

public class RequestManager extends Request{

    static String TB_BASE_URL = "http://10.71.66.102:8001/client";


    public void queryOrder(String userid,RequestCallBack callback){
        String url = TB_BASE_URL+ "/findorder";


        Map<String,String> params = new HashMap<>();

        Map<String,String> rsaMap = new HashMap<>();
        rsaMap.put("user_id", AppManager.getInstance().getUser_id());
        //对用户名 单独加密
        params.put("value",str2rsa(rsaMap));

        this.POST(url,params,callback);
    }
    public void queryOrder(RequestCallBack callback){
        String url = TB_BASE_URL+ "/findorder";

        Map<String,String> params = new HashMap<>();

        Map<String,String> rsaMap = new HashMap<>();
        rsaMap.put("user_id", AppManager.getInstance().getUser_id());
        //对用户名 单独加密
        params.put("value",str2rsa(rsaMap));

        this.POST(url,params,callback);
    }

    public void userinfo(String uuid,String device,RequestCallBack callbck){
        String url = TB_BASE_URL+ "/userinfo";

        Map<String,String> params = new HashMap<>();
        params.put("uuid",uuid);
        params.put("device",device);

        if(AppManager.getInstance().getUserName() != null){
            Map<String,String> rsaMap = new HashMap<>();
            rsaMap.put("username", AppManager.getInstance().getUserName());
            //对用户名 单独加密
            params.put("value",str2rsa(rsaMap));
        }

        this.POST(url,params,callbck);
    }

    public void feedback(String userId,String content, RequestCallBack callBack){
        String url = TB_BASE_URL+ "/feedback";

        Map<String,String> params = new HashMap<String,String>();
        params.put("user_id",userId);
        params.put("content",content);

        this.POST(url,params, callBack);
    }

    //    @param name <#name description#>
//    @param pwd <#pwd description#>
//    @param uuid 可以判断是否单点登录
//    @param device <#device description#>
//    @param success <#success description#>
//    @param failure <#failure description#>
    public void login(String name,String pwd,RequestCallBack callBack){
        String url = TB_BASE_URL+"/login";

//            @{@"username" :name,
//                @"password" :pwd,
//                @"uuid"     :uuid,
//                @"device"   :device};

        Map<String,String> params = new HashMap<String,String>();
        params.put("username",name);
        params.put("password",pwd);
        this.POST(url,mapWithRSA(params), callBack);
    }

    public void register(String name,String pwd,RequestCallBack callBack){
        String url = TB_BASE_URL+"/register";

//            @{@"username" :name,
//                @"password" :pwd,
//                @"uuid"     :uuid,
//                @"device"   :device};

        Map<String,String> params = new HashMap<String,String>();
        params.put("username",name);
        params.put("password",pwd);
        this.POST(url,mapWithRSA(params), callBack);
    }


    //test
    public void test(Map<String, String> params,RequestCallBack callback){

        String url = TB_BASE_URL+"/test";
        String encodeStr = str2rsa(params);

        Map<String,String> par = new HashMap<String,String>();
        par.put("value",encodeStr);
        this.POST(url, par, callback);
    };
// Log.e("POST","输入url:" + url + "\n输入参数:" + encodeStr + "\n输出参数:" + s);

    //test1
    public void test1(Map<String, String> params,RequestCallBack callback){

        String url = TB_BASE_URL+"/test1";
//        String encodeStr = str2rsa(params);
        String str = new JSONObject(params).toString();

        this.POST(url, params, callback);
    };


    private static volatile RequestManager instance;
    public static synchronized RequestManager getInstant(){
        if(instance == null){
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (RequestManager.class){
                if(instance == null) {
                    instance = new RequestManager();
                }
            }
        }
        return instance;
    }


    public  String str2rsa(Map<String,String> map){
        String encryStr = null;
        try {
//            //map转json 字符串
//            Map<String,String> paras = new HashMap<String,String>();
//            paras.put("aa","TEST_API_ANDROID_JNI");
            JSONObject object = new JSONObject(map);
            //得到json字符串
            //object.toString()

            Log.e("RSA encode",object.toString());
            //从 jni中读取字符串
            JniHello hello = new JniHello();
            encryStr= RSA.encryptByPublicKey(object.toString(),RSA.getPublicKey(hello.SayHello()).getEncoded());

            Log.e("RSA公钥加密",encryStr);

//            byte[] decryptBytes= RSA.decryptByPrivateKey(Base64.decode(encryStr.getBytes(),Base64.DEFAULT),RSA.getPrivateKey(RSA.PRIVATEKEY_STRING).getEncoded());
//            String decryStr=new String(decryptBytes);
//            Log.e("RSA私钥解密",decryStr);


//                    byte[] decryptBytes= RSA.decryptByPrivateKey(Base64Decoder.decodeToBytes(encryStr),privateKey.getEncoded());
//                    String decryStr=new String(decryptBytes);
//                    Log.e("MainActivity","私钥解密耗时 cost time---->"+(end-start));
//                    Log.e("MainActivity","解密后json数据 --1-->"+decryStr);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RSA","exception");
        }

        return encryStr;
    }

    private Map<String,String> mapWithRSA(Map<String,String> params){

        String encodeStr = str2rsa(params);
        Map<String,String> map = new HashMap<String,String>();
        map.put("value",encodeStr);
        return map;
    }
//    //json转map
//    public static Map<String, String> getMapForJson(String jsonStr){
//        JSONObject jsonObject ;
//        Map<String, String> valueMap = new HashMap<String, String>();
//        try {
//            jsonObject = new JSONObject(jsonStr);
//
//            Iterator<String> keyIter= jsonObject.keys();
//            String key;
//            String value ;
//
//            while (keyIter.hasNext()) {
//                key = keyIter.next();
//                value = (String) jsonObject.get(key);
//                valueMap.put(key, value);
//            }
//            return valueMap;
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//
//        return valueMap;
//    }
}
