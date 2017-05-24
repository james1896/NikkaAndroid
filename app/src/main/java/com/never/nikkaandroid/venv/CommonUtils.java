package com.never.nikkaandroid.venv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by toby on 20/04/2017.
 */

public class CommonUtils {

    public static final int secondOfHour   =    60*60;
    public static final int secondOfDay    = 24*60*60;
    public static final String collection_userinfo_lasttime = "collection_userinfo_lasttime";


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
    public static float getStatusHeight(Context context) {

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

    public static float getWindowWidth(Activity context){

        WindowManager wm = context.getWindowManager();
        return wm.getDefaultDisplay().getWidth();
    }
    public static float getWindowHeight(Activity context){

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

    //获得设备信息
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

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = (int) getWindowWidth(activity);
        int height = (int) getStatusHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = (int) getWindowWidth(activity);
        int height = (int) getStatusHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }


        public static Map<String, String> getMapForJson(String jsonStr){
        JSONObject jsonObject ;
        Map<String, String> valueMap = new HashMap<String, String>();
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter= jsonObject.keys();
            String key;
            String value ;

            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = (String) jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {

            e.printStackTrace();

        }


       return valueMap;
    }

    /**************************   时间相关   **************************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isIntraday(Context context){
       long last_time = CommonUtils.getLong(context,CommonUtils.collection_userinfo_lasttime);
        Date curDate =  new Date(System.currentTimeMillis());
//        Log.e("curDate", String.valueOf(curDate.getTime()/1000L + "|" + last_time));

        //毫秒
//        Log.e("curDate","c"+System.currentTimeMillis());
        return curDate.getTime()/1000L -last_time < CommonUtils.secondOfDay;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static long getCurrentDate(){

//        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());

        return curDate.getTime()/1000L;
    }

    /***************    获取系统时间  某年 某月 某天 星期几    ***************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getYear(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getMonth(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.YEAR);
        return month;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getDay(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;// Java月份从0开始算
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.YEAR, year);//指定年份
        calendar.set(Calendar.MONTH, month - 1);//指定月份 Java月份从0开始算
        int daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天

        //获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, day);  //指定日
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.e("calendar","year:"+year+" month:"+month+" day:"+day+" week:"+dayOfWeek);
//        String str = "";
//        switch (dayOfWeek)
//        {
//            case 1:
//                str = "星期日";
//                break;
//            case 2:
//                str ="星期一";
//                break;
//            case 3:
//                str = "星期二";
//                break;
//            case 4:
//                str ="星期三";
//                break;
//            case 5:
//                str ="星期四";
//                break;
//            case 6:
//                str = "星期五";
//                break;
//            case 7:
//                str ="星期六";
//                break;
//        }
//        return str;
        return dayOfWeek;
    }



    /****************************************************************************/

    /***********************   sharePreference 数据持久化   ***********************/

    public static String getString(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        String result = setPreferences.getString(strKey, "");
        return result;
    }

    public static String getString(Context context, String strKey, String strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        String result = setPreferences.getString(strKey, strDefault);
        return result;
    }

    public static void saveString(Context context, String strKey, String strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences("app", 0);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putString(strKey, strData);
        editor.commit();
    }

    public static Boolean getBoolean(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        Boolean result = Boolean.valueOf(setPreferences.getBoolean(strKey, false));
        return result;
    }

    public static Boolean getBoolean(Context context, String strKey, Boolean strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        Boolean result = Boolean.valueOf(setPreferences.getBoolean(strKey, strDefault.booleanValue()));
        return result;
    }

    public static void saveBoolean(Context context, String strKey, Boolean strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences("app", 0);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putBoolean(strKey, strData.booleanValue());
        editor.commit();
    }

    public static int getInt(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        int result = setPreferences.getInt(strKey, -1);
        return result;
    }

    public static int getInt(Context context, String strKey, int strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        int result = setPreferences.getInt(strKey, strDefault);
        return result;
    }

    public static void saveInt(Context context, String strKey, int strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences("app", 0);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putInt(strKey, strData);
        editor.commit();
    }

    public static long getLong(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        long result = setPreferences.getLong(strKey, -1L);
        return result;
    }

    public static long getLong(Context context, String strKey, long strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences("app", 0);
        long result = setPreferences.getLong(strKey, strDefault);
        return result;
    }

    public static void saveLong(Context context, String strKey, long strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences("app", 0);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putLong(strKey, strData);
        editor.commit();
    }

    /****************************************************************************/
}
