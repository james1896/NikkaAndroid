package com.never.nikkaandroid.base.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.JsonParse;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActiviy extends AppCompatActivity implements View.OnClickListener{
    private View layout;
    private View login;
    private View register;
    private EditText login_user_edit ;
    private EditText login_pwd_edit ;
    private EditText register_user_edit;
    private EditText register_pwd_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //得到三个界面 隐藏注册和登录
        this.layout = findViewById(R.id.baseLayout);
        this.login = findViewById(R.id.loginLayout);
        this.register = findViewById(R.id.registerLayout);
        this.login.setVisibility(View.GONE);
        this.register.setVisibility(View.GONE);

        //关闭键盘
//        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        ImageView back = (ImageView) findViewById(R.id.base_back);
        Button base_login_btn = (Button) findViewById(R.id.base_login_btn);
        Button base_register_btn = (Button) findViewById(R.id.base_register_btn);

        //
        back.setOnClickListener(this);
        base_login_btn.setOnClickListener(this);
        base_register_btn.setOnClickListener(this);

        //得到主布局 取消键盘
        View main_layout = findViewById(R.id.mainLayout);
        main_layout.setOnClickListener(this);

        //注册登录 输入框
        this.login_user_edit = (EditText) findViewById(R.id.login_user_edit);
        this.login_pwd_edit = (EditText) findViewById(R.id.login_pwd_edit);
        this.register_user_edit = (EditText) findViewById(R.id.register_user_edit);
        this.register_pwd_edit = (EditText) findViewById(R.id.register_pwd_edit);

        //注册 登录 按钮
        findViewById(R.id.login_btn).setOnClickListener(this);
        findViewById(R.id.register_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //取消键盘
            case R.id.mainLayout:
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(this.login_user_edit.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(this.login_pwd_edit.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(this.register_user_edit.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(this.register_pwd_edit.getWindowToken(), 0);
                break;

            case R.id.base_login_btn:{
                this.layout.setVisibility(View.GONE);
                this.login.setVisibility(View.VISIBLE);
                this.register.setVisibility(View.GONE);
                break;
            }

            case R.id.base_register_btn:{
                this.layout.setVisibility(View.GONE);
                this.login.setVisibility(View.GONE);
                this.register.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.base_back:{
                if(this.layout.getVisibility() == View.VISIBLE){
                    finish();
                }
                if(this.login.getVisibility() == View.VISIBLE){
                    this.layout.setVisibility(View.VISIBLE);
                    this.login.setVisibility(View.GONE);
                    this.register.setVisibility(View.GONE);
                }
                if(this.register.getVisibility() == View.VISIBLE){
                    this.layout.setVisibility(View.VISIBLE);
                    this.login.setVisibility(View.GONE);
                    this.register.setVisibility(View.GONE);
                }
                break;
            }

            case R.id.register_btn:{

                break;
            }
            case R.id.login_btn:{
                RequestManager.getInstant().
                        login(this.login_user_edit.getText().toString(),
                                this.login_pwd_edit.getText().toString(),
                                new RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        super.onSuccess(s, call, response);
                        JsonModel model = JsonParse.parser.fromJson(s, new TypeToken<JsonModel>(){}.getType());


                        Log.e("model",model.toString());


                        AppManager.getInstance().setPoints(model.getData().getPoints());
//
                        AppManager.getInstance().setUser_id(model.getUser_id());
                        Log.e("data",model.getData().getPoints() + "&&&&&&&"+ model.getUser_id());
                        AppManager.getInstance().setUserName(LoginActiviy.this.login_user_edit.getText().toString());

//                        finish();
                    }

                });
                break;
            }


        }
    }

    /**
     * Json 转成 Map<>
     * @param jsonStr
     * @return
     */
    public  Map<String, Object> getMapForJson(String jsonStr){
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
            Log.e("", e.toString());
        }
        return null;
    }
}
