package com.example.dllo.yohomix.sqlgreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/9.
 */
@Entity
public class YoHoNew {
    @Id
    private Long id;
    private String url;
    private String data;
    @Generated(hash = 2075429530)
    public YoHoNew(Long id, String url, String data) {
        this.id = id;
        this.url = url;
        this.data = data;
    }
    @Generated(hash = 2070601581)
    public YoHoNew() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
