package com.never.nikkaandroid.home;


import android.app.Fragment;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.RSA;
import com.never.nikkaandroid.base.BaseFragment;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import sun.misc.BASE64Decoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements OnClickListener{


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
//        Intent intent = new Intent(getActivity(),BalanceActivity.class);
//        startActivity(intent);

        View v1 = contentView.findViewById(R.id.payLayout);
        v1.setOnClickListener(this);

        View v2 = contentView.findViewById(R.id.youhuiLayout);
        v2.setOnClickListener(this);

        View v3 = contentView.findViewById(R.id.recordLayout);
        v3.setOnClickListener(this);

    }
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.payLayout:{
                Intent intent = new Intent(getActivity(),BalanceActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            case R.id.youhuiLayout:{
                try {
                    KeyPair keyPair=RSA.generateRSAKeyPair(RSA.DEFAULT_KEY_SIZE);
                    // 公钥
                    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                    // 私钥
                    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

//                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqgKQmqFeq0J6Vr+d90A0jlkkG5DkNYyShGj+IY9dV79T8q/cnziWnfYovZum6Vo7k83KN9tWWUEGI6NQgdY861tQ9WSQGdMiG7Oli94z6wYsKCvMZjPv7jeEY0pdLgDkr71g7/KrKPtXLmBz7LINDOE18pcKrjl/RTrOYtDo3PQIDAQAB"
//                    byte[] encryptBytes= RSA.encryptByPublicKey("test".getBytes(),publicKey.getEncoded());
                    byte[] encryptBytes= RSA.encryptByPublicKey("test".getBytes(),getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqgKQmqFeq0J6Vr+d90A0jlkkG5DkNYyShGj+IY9dV79T8q/cnziWnfYovZum6Vo7k83KN9tWWUEGI6NQgdY861tQ9WSQGdMiG7Oli94z6wYsKCvMZjPv7jeEY0pdLgDkr71g7/KrKPtXLmBz7LINDOE18pcKrjl/RTrOYtDo3PQIDAQAB").getEncoded());
                    String encryStr = new String(Base64.encode(encryptBytes,Base64.DEFAULT));
                    Log.e("RSA公钥加密",encryStr);

                    byte[] decryptBytes= RSA.decryptByPrivateKey(Base64.decode(encryStr.getBytes(),Base64.DEFAULT),getPrivateKey(RSA.PRIVATEKEY_STRING).getEncoded());
                    String decryStr=new String(decryptBytes);
                    Log.e("RSA私钥解密",decryStr);


//                    byte[] decryptBytes= RSA.decryptByPrivateKey(Base64Decoder.decodeToBytes(encryStr),privateKey.getEncoded());
//                    String decryStr=new String(decryptBytes);
//                    Log.e("MainActivity","私钥解密耗时 cost time---->"+(end-start));
//                    Log.e("MainActivity","解密后json数据 --1-->"+decryStr);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("RSA","exception");
                }
                Log.e("request","okgo");
//                OkGo.get("http://10.66.67.81:8001/client/test1")     // 请求方式和请求url
//                        .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
//                        .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                        .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(String s, okhttp3.Call call, Response response) {
//                                log.e("okgo",s);
//                            }
//                        });
                break;
            }
            case R.id.recordLayout:{
                Intent intent = new Intent(getActivity(),RecordActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}
