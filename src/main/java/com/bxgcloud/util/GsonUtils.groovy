package com.bxgcloud.util

import com.google.gson.GsonBuilder;

/**
 * Created by zdming on 2016/8/31.
 */
class GsonUtils {
    public static String toJson(Object obj) {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss") .create().toJson( obj).replaceAll(":null",":\"\"");
    }

    public static String toNormalJson(Object obj) {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(obj).replaceAll("null","\"\"");
    }
}
