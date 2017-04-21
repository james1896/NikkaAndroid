package com.never.nikkaandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;

import java.util.UUID;

/**
 * Created by toby on 20/04/2017.
 */

public class CommonUtils {

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

    //生成唯一码
    public static String getUniquePsuedoID(){
        String m_szDevIDShort = "35" +

                Build.BOARD.length()%10 +
                Build.BRAND.length()%10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() %10 +
                Build.DISPLAY.length() %10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;

        String serial = Build.SERIAL;
        return new UUID(m_szDevIDShort.hashCode(),serial.hashCode()).toString();

    }
}
