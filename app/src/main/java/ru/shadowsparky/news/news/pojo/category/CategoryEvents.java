package ru.shadowsparky.news.news.pojo.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryEvents {
    @SerializedName("events")
    List<CategoryResponse> events;

    public List<CategoryResponse> getEvents() {
        return events;
    }
}
