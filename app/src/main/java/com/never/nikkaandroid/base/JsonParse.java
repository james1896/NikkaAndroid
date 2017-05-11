package com.never.nikkaandroid.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by toby on 11/05/2017.
 */

public class JsonParse {

    public static final Gson parser = new GsonBuilder().serializeNulls().create();

}
