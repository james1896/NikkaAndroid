package com.never.nikkaandroid;

/**
 * Created by toby on 06/04/2017.
 */

public class UserDataManager {
        //私有的静态变量
        private static UserDataManager instance;
        //私有的构造方法
        private UserDataManager (){};
        //公有的同步静态方法
        public static synchronized UserDataManager getInstance() {
            if (instance == null) {
                instance = new UserDataManager();
            }
            return instance;
        }
}
