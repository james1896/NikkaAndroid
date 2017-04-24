package com.never.nikkaandroid.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.never.nikkaandroid.R;

public class LoginActiviy extends AppCompatActivity implements View.OnClickListener{
    private View layout;
    private View login;
    private View register;
    private EditText login_user_edit ;
    private EditText login_pwd_edit ;
    private EditText register_user_edit ;
    private EditText register_pwd_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activiy);

        this.layout = findViewById(R.id.baseLayout);
        this.login = findViewById(R.id.loginLayout);
        this.register = findViewById(R.id.registerLayout);


//        layout.setVisibility(View.GONE);
        this.login.setVisibility(View.GONE);
        this.register.setVisibility(View.GONE);

        //关闭键盘
//        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        ImageView back = (ImageView) findViewById(R.id.login_back);
        Button base_login_btn = (Button) findViewById(R.id.base_login_btn);
        Button base_register_btn = (Button) findViewById(R.id.base_register_btn);

        back.setOnClickListener(this);
        base_login_btn.setOnClickListener(this);
        base_register_btn.setOnClickListener(this);


        View main_layout = findViewById(R.id.mainLayout);
        main_layout.setOnClickListener(this);

        this.login_user_edit = (EditText) findViewById(R.id.login_user_edit);
        this.login_pwd_edit = (EditText) findViewById(R.id.login_pwd_edit);
        this.register_user_edit = (EditText) findViewById(R.id.register_user_edit);
        this.register_pwd_edit = (EditText) findViewById(R.id.register_pwd_edit);
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

            case R.id.login_back:{
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

        }
    }
}
