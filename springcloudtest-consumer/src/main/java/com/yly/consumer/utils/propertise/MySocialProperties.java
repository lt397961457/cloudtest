package com.yly.consumer.utils.propertise;

public class MySocialProperties {
    private String filterProcessUrl = "/auth";  //默认是"/auth"
    private QQProperties qq = new QQProperties();

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
