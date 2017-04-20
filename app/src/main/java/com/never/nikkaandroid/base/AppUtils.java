package com.never.nikkaandroid.base;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by toby on 20/04/2017.
 */

public class AppUtils {

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static int getWindowWidth(Activity context){

        WindowManager wm = context.getWindowManager();
        return wm.getDefaultDisplay().getWidth();
    }
    public static int getWindowHeight(Activity context){

        WindowManager wm = context.getWindowManager();
        return wm.getDefaultDisplay().getHeight();
    }

  ;
}
