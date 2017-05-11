package com.never.nikkaandroid.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Rea.X on 2017/2/1.
 */

public class JsonParse {

    public static final Gson parser = new GsonBuilder().serializeNulls().create();

}
