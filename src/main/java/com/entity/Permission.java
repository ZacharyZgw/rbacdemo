package com.entity;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer id;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if(url==null || url==""){
            this.url = null;
        }else {
            this.url = url.trim();
        }
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
