package com.example.dllo.yohomix.recommend;

import com.google.gson.Gson;

import java.util.HashMap;
//yoho潮流志post请求body体中value的封装
public class PostBody {
    //把map转化成String
    public String m(PostBody postBody) {
        Gson gson = new Gson();
        String value = gson.toJson(postBody).toString();
        return value;
    }
    private String channelId, limit, startTime, refreshType, ruleTime,
            localNum, platform, locale, language, udid, curVersion, authInfo;

    public PostBody() {
        this.limit = "12";
        this.curVersion = "5.0.4";
        this.language = "zh-Hans";
        this.locale = "zh-Hans";
        this.platform = "4";
        this.localNum = "0";
        this.udid = "00000000000000063aa461b71c4cfcf";
        this.refreshType = "1";
        this.startTime = "0";
        this.ruleTime = "0";
        //接口中 value嵌套的key value
        HashMap<String, String> map = new HashMap<>();
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        String value = new Gson().toJson(map).toString();
        this.authInfo = value;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


}
