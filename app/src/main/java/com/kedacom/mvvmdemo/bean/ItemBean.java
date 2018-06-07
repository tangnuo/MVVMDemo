package com.kedacom.mvvmdemo.bean;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/7 13:40
 */
public class ItemBean {
    private String itemContent;
    private Class cls;

    public ItemBean(String itemContent, Class cls) {
        this.itemContent = itemContent;
        this.cls = cls;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

}
