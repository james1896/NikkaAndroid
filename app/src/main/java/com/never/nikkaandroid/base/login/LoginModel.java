package com.never.nikkaandroid.base.login;

import com.google.gson.annotations.SerializedName;
import com.never.nikkaandroid.base.model.BaseModel;

/**
 * Created by toby on 11/05/2017.
 */

public class LoginModel extends BaseModel {

    @SerializedName("data")
    private Data data;

    @Override
    public String toString() {
        return "LoginModel{" +
                "statusCode=" + statusCode +
                ", userToken='" + userToken + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        @SerializedName("points")
        private float points;

        @SerializedName("user_id")
        private String user_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public float getPoints() {
            return points;
        }

        public void setPoints(float points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "points=" + points +
                    ", user_id='" + user_id + '\'' +
                    '}';
        }
    }
}


