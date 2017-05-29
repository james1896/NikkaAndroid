package com.never.nikkaandroid.base.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by toby on 29/05/2017.
 */

public class NetTypeReceiver extends BroadcastReceiver {
    String type = "";
    Context context;

    public NetTypeReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                String name = networkInfo.getTypeName();
                switch (networkInfo.getType()) {
                    case 0://移动 网络
                        if (TextUtils.isEmpty(type)) {
                            type = name;
                        }
                        break;
                    case 1://wifi网络
                        if (TextUtils.isEmpty(type)) {
                            type = name;
                        }
                        break;
                }
            } else {// 无网络
                //network was unavailable, stop the streamer, and show a dialog
//                showNetworkInvalidView(R.string.network_error_message);
                type = "";
            }
        }

        Log.e("netType",(TextUtils.isEmpty(type)?"当前无网络":type));
    }
}
