package com.never.nikkaandroid.me;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseActivity;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;

import okhttp3.Call;
import okhttp3.Response;

public class FeedbackActivity extends BaseActivity {

    TextView textNumber;
    EditText editText;
    @Override
    protected int getContentView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void init() {

        setNavbar("FeedBack",getResources().getColor(R.color.theme_pink));


        editText = (EditText) findViewById(R.id.edittext);
        textNumber = (TextView) findViewById(R.id.feedback_textview);
        Button sumbit = (Button) findViewById(R.id.feedback_submit);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestManager.getInstant().feedback(AppManager.getInstance().getUser_id(), editText.getText().toString(), new RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        super.onSuccess(s, call, response);

                        Log.e("feedback",s);
                    }
                });
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.e("count",""+count);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("count",""+editText.getText().length());

                String text = editText.getText().toString();
                if(text.length()> 300){
                   editText.setText(text.substring(0,300));
                }
                textNumber.setText(editText.getText().toString().length()+"/300");
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.e("count",""+count);
            }
        });


        View mainView = findViewById(R.id.feedback_layout);
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });
    }
}
