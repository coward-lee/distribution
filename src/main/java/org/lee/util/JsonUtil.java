package org.lee.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static <T>  T parse(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }

    public static <T> String toJson(T obj){
        return JSON.toJSONString(obj);
    }
}
