package com.mxxy.game.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JsonUtils
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class JsonUtils {

    private final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static JsonUtils jsonUtils;

    public static JsonUtils getInstanceJsonUtils() {
        if (jsonUtils == null) {
            jsonUtils = new JsonUtils();
            jsonUtils.initGson();
        }
        return jsonUtils;
    }

    public static <T> T parses(String json, Class<T> objectClass) {
        if (StringUtils.isNotBlank(json)) {
            T t = gson.fromJson(json, objectClass);
            return t;
        }
        return null;
    }

    public Gson initGson() {
        return gson;
    }
}
