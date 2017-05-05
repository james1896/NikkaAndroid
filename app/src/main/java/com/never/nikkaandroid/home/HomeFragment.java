package com.never.nikkaandroid.home;


import android.app.Fragment;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;
import com.never.nikkaandroid.base.JniHello;
import com.never.nikkaandroid.databinding.FragmentHomeBinding;
import com.never.nikkaandroid.venv.RSA;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements OnClickListener{


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

//        View v1 = contentView.findViewById(R.id.payTextView);
        View v1 = dataBind.payTextView;
        v1.setOnClickListener(this);

//        View v2 = contentView.findViewById(R.id.youhuiTextView);
        View v2 = dataBind.youhuiTextView;
        v2.setOnClickListener(this);

//        View v3 = contentView.findViewById(R.id.recordTextView);
        View v3 = dataBind.recordTextView;
        v3.setOnClickListener(this);

        JniHello hello = new JniHello();
//        hello.stringMethod("jni  hola");
//        Log.e("JNI",);
        Log.e("JNI","BOOL"+hello.booleanMethod(true));
        Log.e("JNI",""+hello.intMethod(100));

        Log.e("JNI",hello.serialWithUserID(136475637));

//        int[] m = { 1, 2, 3 };
//        Log.e("JNI",""+hello.intArrayMethod(m));
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.payTextView:{
                Intent intent = new Intent(getActivity(),BalanceActivity.class);
                getActivity().startActivity(intent);

//                Intent intent = new Intent(getActivity(),LoginActiviy.class);
//                getActivity().startActivity(intent);
                break;
            }
            case R.id.youhuiTextView:{
                String encryStr = null;
                try {
                    //map转json 字符串
                    Map<String,String> paras = new HashMap<String,String>();
                    paras.put("aa","TEST_API_ANDROID_JNI");
                    JSONObject object = new JSONObject(paras);
                    //得到json字符串
                    //object.toString()

                    //从 jni中读取字符串
                    JniHello hello = new JniHello();
                     encryStr= RSA.encryptByPublicKey(object.toString(),RSA.getPublicKey(hello.SayHello()).getEncoded());

                    Log.e("RSA公钥加密",encryStr);

                    byte[] decryptBytes= RSA.decryptByPrivateKey(Base64.decode(encryStr.getBytes(),Base64.DEFAULT),RSA.getPrivateKey(RSA.PRIVATEKEY_STRING).getEncoded());
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

                OkGo.post("http://10.66.67.81:8001/client/test")    // 请求方式和请求url, get请求不需要拼接参数，支持get，post，put，delete，head，options请求
                        .tag(this)               // 请求的 tag, 主要用于取消对应的请求
                        .isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                        .connTimeOut(10000)      // 设置当前请求的连接超时时间
                        .readTimeOut(10000)      // 设置当前请求的读取超时时间
                        .writeTimeOut(10000)     // 设置当前请求的写入超时时间
                        .cacheKey("cacheKey")    // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                        .cacheTime(5000)         // 缓存的过期时间,单位毫秒
                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST) // 缓存模式，详细请看第四部分，缓存介绍
                        .headers("header1", "headerValue1")     		// 添加请求头参数
                        .headers("header2", "headerValue2")     		// 支持多请求头参数同时添加
                        .params("value", encryStr)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.e("POST response",s);

                                //json转map
                                Map map = getMapForJson(s);
                                Log.e("map","map_POST:"+map.get("aa"));
                            }
                        });
                break;
            }
            case R.id.recordTextView:{
                Intent intent = new Intent(getActivity(),RecordActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            default:
                break;
        }
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
}
