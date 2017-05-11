package com.never.nikkaandroid.base.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toby on 11/05/2017.
 */

public class JsonModel {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private aaa data;

    @Override
    public String toString() {
        return "JsonModel{" +
                "user_id='" + user_id + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public aaa getData() {
        return data;
    }

    public void setData(aaa data) {
        this.data = data;
    }

    public static class aaa{
        @SerializedName("points")
        private float points;

        public float getPoints() {
            return points;
        }

        public void setPoints(float points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return "aaa{" +
                    "points=" + points +
                    '}';
        }
    }
}


