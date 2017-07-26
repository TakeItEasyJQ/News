package com.example.news;

/**
 * Created by My Computer on 2017/7/25.
 */
public class Content {
    private boolean hasPic;
    private String date;
    private String title;
    private String picUrl;
    private String shortContent;
    private String linkUrl;
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isHasPic() {
        return hasPic;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getShortContent() {
        return shortContent;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setHasPic(boolean hasPic) {
        this.hasPic = hasPic;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
