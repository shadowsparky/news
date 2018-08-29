package ru.shadowsparky.news.news.pojo.category;

import java.io.Serializable;

public class CategoryResponse implements Serializable {
    private String title = "";
    private String coefficient = "";
    private String time = "";
    private String place = "";
    private String preview = "";
    private String article = "";

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getCoefficient() {
        return coefficient;
    }

//    public void setCoefficient(String coefficient) {
//        this.coefficient = coefficient;
//    }

    public String getTime() {
        return time;
    }

//    public void setTime(String time) {
//        this.time = time;
//    }

    public String getPlace() {
        return place;
    }

//    public void setPlace(String place) {
//        this.place = place;
//    }

    public String getPreview() {
        return preview;
    }

//    public void setPreview(String preview) {
//        this.preview = preview;
//    }

    public String getArticle() {
        return article;
    }

//    public void setArticle(String article) {
//        this.article = article;
//    }
}

