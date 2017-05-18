package com.never.nikkaandroid.base.login;

import java.io.Serializable;

/**
 * Created by toby on 18/05/2017.
 */

public class LoginModel implements Serializable {
//    {'points': user.points,
//            'userToken':tokenHandle.getToken(),
//            'user_id': user.user_id}}

    public float points;
    public  String userToken;
    public  String user_id;

    @Override
    public String toString() {
        return "LoginModel{" +
                "points='" + points + '\'' +
                ", userToken='" + userToken + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
