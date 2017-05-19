package com.never.nikkaandroid.home;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by toby on 18/05/2017.
 */

public class RecordModel implements Serializable{


    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public String price;
    @SerializedName("time")
    public String time;

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
