package ru.shadowsparky.news.MVP.fragments.category;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shadowsparky.news.Requester;
import ru.shadowsparky.news.api.Api;
import ru.shadowsparky.news.pojo.category.CategoryEvents;

public class CategoryModel implements Category.Model {
    interface RequestCallback {
        void handleRequest(CategoryEvents events);
    }

    @Override
    public void getCategoryRequest(RequestCallback callback, String category) {
        Api api = new Requester().getApi();
        Observable.just("mock")
        .observeOn(Schedulers.io())
        .map(item -> api.getCategory(category))
        .map(item -> item.blockingFirst())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                next -> callback.handleRequest(next),
                error -> {
                    Log.println(Log.DEBUG, "MAIN_TAG", error.toString());
                    callback.handleRequest(null);
                }
        );
    }
}
