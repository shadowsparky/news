package ru.shadowsparky.news.MVP.fragments.category;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shadowsparky.news.Requester;
import ru.shadowsparky.news.api.Api;
import ru.shadowsparky.news.callbacks.ResponseHandler;

public class CategoryModel implements Category.Model {

    @Override
    public void getCategoryRequest(ResponseHandler callback, String category) {
        Api api = new Requester().getApi();
        Observable.just(category)
        .observeOn(Schedulers.io())
        .map(item -> api.getCategory(item).blockingFirst())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                next -> callback.handle(next),
                error -> {
                    callback.handle(null);
                    Log.println(Log.DEBUG, "MAIN_TAG", error.toString());
                }
        );
    }
}
