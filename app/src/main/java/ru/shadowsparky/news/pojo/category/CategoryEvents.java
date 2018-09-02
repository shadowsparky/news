package ru.shadowsparky.news.pojo.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.shadowsparky.news.callbacks.Response;

public class CategoryEvents implements Response {
    @SerializedName("events")
    List<CategoryResponse> events;

    public List<CategoryResponse> getEvents() {
        return events;
    }
}
