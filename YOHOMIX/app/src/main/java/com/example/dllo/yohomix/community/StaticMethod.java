package com.example.dllo.yohomix.community;

/**
 * Created by dllo on 16/12/2.
 */

public class StaticMethod {
    public static String getSubstring(String url){
        String a = url.substring(0,url.indexOf("?"));
        return a;
    }
}
