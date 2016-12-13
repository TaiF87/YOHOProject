package com.example.dllo.yohomix.sqlgreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/13.
 */
@Entity
public class YoHoColumns {
    @Id
    private Long id;
    private int type;
    private String title,imgUrl,webUrl,tagName,time,videoUrl;
    @Generated(hash = 853143733)
    public YoHoColumns(Long id, int type, String title, String imgUrl,
            String webUrl, String tagName, String time, String videoUrl) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.imgUrl = imgUrl;
        this.webUrl = webUrl;
        this.tagName = tagName;
        this.time = time;
        this.videoUrl = videoUrl;
    }
    @Generated(hash = 35659798)
    public YoHoColumns() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getWebUrl() {
        return this.webUrl;
    }
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
