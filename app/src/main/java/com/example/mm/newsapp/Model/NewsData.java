package com.example.mm.newsapp.Model;

public class NewsData {

    private String newsTitle;
    private String newsImageUrl;
    private String newsDetail;
    private String newsUrl;
    private String content;

    public NewsData(String newsTitle, String newsImageUrl, String newsDetail, String newsUrl, String content) {
        this.newsTitle = newsTitle;
        this.newsImageUrl = newsImageUrl;
        this.newsDetail = newsDetail;
        this.newsUrl = newsUrl;
        this.content = content;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getContent() {
        return content;
    }
}
