package ru.shadowsparky.news.pojo.category;

import java.io.Serializable;

import ru.shadowsparky.news.callbacks.Response;

public class CategoryResponse implements Response {
    private String title = "";
    private String coefficient = "";
    private String time = "";
    private String place = "";
    private String preview = "";
    private String article = "";

    public String getTitle() {
        return title;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getPreview() {
        return preview;
    }

    public String getArticle() {
        return article;
    }
}

