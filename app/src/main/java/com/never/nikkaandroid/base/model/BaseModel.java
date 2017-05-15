package com.never.nikkaandroid.base.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toby on 15/05/2017.
 */

public class BaseModel {

    @SerializedName("status")
    protected int status;

    @SerializedName("userToken")
    protected String userToken;
}
