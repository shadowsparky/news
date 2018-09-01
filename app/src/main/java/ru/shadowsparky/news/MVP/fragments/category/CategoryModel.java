package ru.shadowsparky.news.MVP.fragments.category;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shadowsparky.news.Requester;
import ru.shadowsparky.news.api.Api;

public class CategoryModel implements Category.Model {

    @Override
    public void getCategoryRequest(Category.RequestCallback callback, String category) {
        Api api = new Requester().getApi();
        Observable.just(category)
        .observeOn(Schedulers.io())
        .map(item -> api.getCategory(item).blockingFirst())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                next -> callback.handleRequest(next),
                error -> {
                    callback.handleRequest(null);
                    Log.println(Log.DEBUG, "MAIN_TAG", error.toString());
                }
        );
    }
}
