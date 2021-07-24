package com.example.newsappver2.Model;

import java.util.List;

public class Website {
    private  String status;
    private int totalResult;
    private List<TopHeadlines> topHeadlines;//list articles di top headlines, error gnti
    private List<Article> articles;
    private Source source;

    public Website(){

    }

    public Website(String status, int totalResult, List<TopHeadlines> topHeadlines, Source source) {
        this.status = status;
        this.totalResult = totalResult;
        this.topHeadlines = topHeadlines;
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<TopHeadlines> getTopHeadlines() {
        return topHeadlines;
    }

    public void setArticles(List<Article> articles){
        this.articles=articles;
    }

    public List<Article>getArticles(){
        return articles;
    }

    public void setTopHeadlines(List<TopHeadlines> topHeadlines) {
        this.topHeadlines = topHeadlines;
    }

    public Source getSource(Article article) {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
