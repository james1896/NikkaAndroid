package com.never.nikkaandroid.venv;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.never.nikkaandroid.NikkaApplication;

/**
 * Created by toby on 06/04/2017.
 */

public class AppManager extends  Object{

    private String save_userid_key = "save_userid_key";
    private String save_username_key = "save_username_key";

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

    /******************     user_id      **************************/
    public String getUser_id() {
        this.user_id = CommonUtils.getString(NikkaApplication.getContext(),save_userid_key);
        return user_id;
    }

    public void setUser_id(String user_id) {
        CommonUtils.saveString(NikkaApplication.getContext(),save_userid_key,user_id);
        this.user_id = user_id;
    }

    /******************************************************************/
    protected  Boolean isLogin;
    //私有的静态变量


    public Boolean getLogin() {
        if(this.getUser_id() != null && this.getUser_id() != "")
            return true;
        return false;
    }
    /******************     UserName      **************************/
    public String getUserName() {
        this.userName = CommonUtils.getString(NikkaApplication.getContext(),save_username_key);
        return this.userName;
    }

    public void setUserName(String userName) {
        CommonUtils.saveString(NikkaApplication.getContext(),save_username_key,userName);
        this.userName = userName;
    }

    /******************************************************************/


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
