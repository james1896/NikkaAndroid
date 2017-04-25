package com.never.nikkaandroid.venv;

/**
 * Created by toby on 06/04/2017.
 */

public class AppDataManager {

    public int screenWidth;
    public  int screenHeight;
        //私有的静态变量
        private static AppDataManager instance;
        //私有的构造方法
        private AppDataManager(){};
        //公有的同步静态方法
        public static synchronized AppDataManager getInstance() {
            if (instance == null) {
                instance = new AppDataManager();
            }
            return instance;
        }
}
