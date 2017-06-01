package com.never.nikkaandroid.home;


import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;
import com.never.nikkaandroid.base.JniHello;
import com.never.nikkaandroid.base.login.LoginActiviy;
import com.never.nikkaandroid.databinding.FragmentHomeBinding;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements OnClickListener{


    public HomeFragment() {
        // Required empty public constructor
        Log.e("aa","aa");
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


                if(AppManager.getInstance().getLogin()){
                    Intent intents = new Intent(getActivity(),BalanceActivity.class);
                    getActivity().startActivity(intents);
                }else {

                    Intent intent = new Intent(getActivity(),LoginActiviy.class);
                    getActivity().startActivity(intent);
                }

                break;
            }
            case R.id.youhuiTextView:{
                Map<String,String> paras = new HashMap<String,String>();
                paras.put("aa","TEST_API_ANDROID_JNI");

                Map<String,String> paras1 = new HashMap<String,String>();
                paras1.put("test1","TEST1_API_ANDROID_JNI");

                RequestManager.getInstant().test(paras, new RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        super.onSuccess(s, call, response);
                        Log.e("test","ok");
                    }
                });

//                new RequestManager().test1(paras1, new RequestCallBack() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        super.onSuccess(s, call, response);
//                        Log.e("test1","ok");
//                    }
//                });

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

}
