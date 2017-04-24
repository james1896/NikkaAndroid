package com.never.nikkaandroid.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by toby on 20/04/2017.
 */

public class CommonUtils {

//    px与dip的概念及互相转化
//    px即pixels，是绝对像素，有多少个像素点就是多少个像素点。
//    dip即device independent pixel，设备独立像素，无像素无关。
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

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

                //主板
                Build.BOARD.length() % 10   +

                //android系统定制商
                Build.BRAND.length() % 10   +

                //cpu指令集
                Build.CPU_ABI.length() % 10 +

                //设备参数
                Build.DEVICE.length() % 10  +

                //显示屏参数
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10    +

                //修订版本列表
                Build.ID.length() % 10      +

                //硬件制造商
                Build.MANUFACTURER.length() % 10 +

                //版本
                Build.MODEL.length() % 10   +

                //手机制造商
                Build.PRODUCT.length() % 10 +

                //描述build的标签
                Build.TAGS.length() % 10    +

                //builder类型
                Build.TYPE.length() % 10    +
                //
                Build.USER.length() % 10;

        String serial = Build.SERIAL;
        return new UUID(m_szDevIDShort.hashCode(),serial.hashCode()).toString();

    }

    public static String collectDeviceInfo(Context ctx) {

        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("collect", "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
//                infos.put(field.getName(), field.get(null).toString());
                Log.e("collectDeviceInfo", field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e("collect", "an error occured when collect crash info", e);
            }
        }

        return Build.BRAND +" | " + Build.MODEL;

    }
}
