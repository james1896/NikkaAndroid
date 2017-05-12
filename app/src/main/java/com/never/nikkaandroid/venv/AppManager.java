package com.never.nikkaandroid.venv;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by toby on 06/04/2017.
 */

public class AppManager {

    protected String userName;
    protected float points;
    protected String user_id;
    protected String user_token;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    protected  Boolean isLogin;
    //私有的静态变量


    public Boolean getLogin() {
        if(userName != null)
            return true;
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    private static AppManager instance;
    //私有的构造方法
    private AppManager(){};
    //公有的同步静态方法
    public static synchronized AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }




    public static class DeviceInfo{
        /**
         * 设置当前activity的屏幕亮度
         *
         * @param paramFloat 0-1.0f
         * @param activity   需要调整亮度的activity
         */
        public static void setActivityBrightness(float paramFloat, Activity activity) {
            Window localWindow = activity.getWindow();
            WindowManager.LayoutParams params = localWindow.getAttributes();
            params.screenBrightness = paramFloat;
            localWindow.setAttributes(params);
        }

        /**
         * 获取当前activity的屏幕亮度
         *
         * @param activity 当前的activity对象
         * @return 亮度值范围为0-0.1f，如果为-1.0，则亮度与全局同步。
         */
        public static float getActivityBrightness(Activity activity) {
            Window localWindow = activity.getWindow();
            WindowManager.LayoutParams params = localWindow.getAttributes();
            return params.screenBrightness;
        }
    }
}
