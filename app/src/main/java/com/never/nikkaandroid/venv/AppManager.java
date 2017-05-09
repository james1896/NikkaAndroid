package com.never.nikkaandroid.venv;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by toby on 06/04/2017.
 */

public class AppManager {

    protected String userName;
    protected  Boolean isLogin;
    //私有的静态变量


    public Boolean getLogin() {
        if(userName != null)
            return true;
        return false;
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
