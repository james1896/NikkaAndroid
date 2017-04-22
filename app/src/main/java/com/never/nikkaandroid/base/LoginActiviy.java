package com.never.nikkaandroid.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.never.nikkaandroid.R;

public class LoginActiviy extends AppCompatActivity implements View.OnClickListener{
    private View layout;
    private View login;
    private View register;
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

        Button back = (Button) findViewById(R.id.login_back);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);

        back.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:{
                this.layout.setVisibility(View.GONE);
                this.login.setVisibility(View.VISIBLE);
                this.register.setVisibility(View.GONE);
                break;
            }
            case R.id.registerBtn:{
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
