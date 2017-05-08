package com.never.nikkaandroid.venv.request;

import android.util.Log;

import com.never.nikkaandroid.base.JniHello;
import com.never.nikkaandroid.venv.RSA;

import org.json.JSONObject;

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

    public void test(Map<String, String> params){

        String url = TB_BASE_URL+"/test";
        String encodeStr = str2rsa(params);
        this.POST(url,encodeStr,encodeStr);
    };

    public void test1(Map<String, String> params){

        String url = TB_BASE_URL+"/test1";
//        String encodeStr = str2rsa(params);
        String str = new JSONObject(params).toString();

        this.POST(url,str,str);
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
}
