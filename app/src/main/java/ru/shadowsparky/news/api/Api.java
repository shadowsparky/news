package ru.shadowsparky.news.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.shadowsparky.news.pojo.category.CategoryEvents;

public interface Api {
    @GET("list.php")
    Observable<CategoryEvents> getCategory(@Query("category") String category);
}
