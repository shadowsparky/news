package ru.shadowsparky.news.pojo.event_view;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.shadowsparky.news.callbacks.Response;

public class EventArticle implements Serializable, Response {
    @SerializedName("header")
    String header = "";
    String text = "";

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public EventArticle setHeader(String header) {
        this.header = header;
        return this;
    }

    public EventArticle setText(String text) {
        this.text = text;
        return this;
    }
}
