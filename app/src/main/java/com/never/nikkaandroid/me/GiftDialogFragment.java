package com.never.nikkaandroid.me;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.CommonUtils;
import com.never.nikkaandroid.venv.request.RequestCallBack;
import com.never.nikkaandroid.venv.request.RequestManager;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by toby on 27/05/2017.
 */

public class GiftDialogFragment extends DialogFragment {

    private View rootView;
    private ViewGroup vg;
    public String userName;
    public String avaPoints;
    public String tranPoints;

    public EditText user_edit ;
    public EditText tranPoints_edit;

    public GiftInterface getGiftInterface() {
        return giftInterface;
    }

    public void setGiftInterface(GiftInterface giftInterface) {
        this.giftInterface = giftInterface;
    }

    private GiftInterface giftInterface;

    public GiftDialogFragment(String userName, String avaPoints, String tranPoints) {
        this.userName = userName;
        this.avaPoints = avaPoints;
        this.tranPoints = tranPoints;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.gift_dialog,container,false);


        return this.rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void hiddenkeyboard(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(GiftDialogFragment.this.user_edit.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(GiftDialogFragment.this.tranPoints_edit.getWindowToken(), 0);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        this.user_edit = (EditText) this.rootView.findViewById(R.id.gift_edit_username);
        this.tranPoints_edit = (EditText) this.rootView.findViewById(R.id.gift_edit_tranpoint);
        TextView avaPoints_tv = (TextView) this.rootView.findViewById(R.id.gift_textview_avapoints);

        SpannableString user_spannable = new SpannableString(this.userName);//这里输入自己想要的提示文字
        user_edit.setHint(user_spannable);
        SpannableString tranPoints_spannable = new SpannableString(this.tranPoints);//这里输入自己想要的提示文字
        tranPoints_edit.setHint(tranPoints_spannable);
        avaPoints_tv.setText(this.avaPoints);

        //取消键盘
        this.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               GiftDialogFragment.this.hiddenkeyboard();

            }
        });



         this.rootView.findViewById(R.id.gift_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftDialogFragment.this.hiddenkeyboard();
                RequestManager.getInstant().transformPoint(AppManager.getInstance().getUser_id(), "Rea", tranPoints_edit.getText().toString(), new RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        super.onSuccess(s, call, response);

                        AppManager.getInstance().setPoints(CommonUtils.convertToFloat(tranPoints_edit.getText().toString(),0f));

                        GiftDialogFragment.this.giftInterface.inputLoginInforCompleted();
                        GiftDialogFragment.this.dismiss();
                    }
                });
            }
        });

    }


}
