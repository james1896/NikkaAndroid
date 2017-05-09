package com.never.nikkaandroid.venv.request;

import android.util.Log;

import com.never.nikkaandroid.base.JniHello;
import com.never.nikkaandroid.venv.RSA;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by toby on 08/05/2017.
 */

public class RequestManager extends Request{

    static String TB_BASE_URL = "http://10.71.66.102:8001/client";


//    public static void login(String name,String pwd,String uuid,String device){
//        this.POST("http://10.66.67.81:8001/client/test",);
//
//    }

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
    public void test(Map<String, String> params,RequestCallBack callback){

        final String url = TB_BASE_URL+"/test";
        final String encodeStr = str2rsa(params);

        Map<String,String> par = new HashMap<String,String>();
        par.put("value",encodeStr);
        this.POST(url, par, encodeStr, callback);
    };
// Log.e("POST","输入url:" + url + "\n输入参数:" + encodeStr + "\n输出参数:" + s);
    public void test1(Map<String, String> params,RequestCallBack callback){

        String url = TB_BASE_URL+"/test1";
//        String encodeStr = str2rsa(params);
        String str = new JSONObject(params).toString();

        this.POST(url, params, str, callback);
    };


    public static String str2rsa(Map<String,String> map){
        String encryStr = null;
        try {
//            //map转json 字符串
//            Map<String,String> paras = new HashMap<String,String>();
//            paras.put("aa","TEST_API_ANDROID_JNI");
            JSONObject object = new JSONObject(map);
            //得到json字符串
            //object.toString()

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
