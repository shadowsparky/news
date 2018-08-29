package ru.shadowsparky.news.pojo.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryEvents implements Serializable {
    @SerializedName("events")
    List<CategoryResponse> events;

    public List<CategoryResponse> getEvents() {
        return events;
    }
}
