package ru.shadowsparky.news.MVP.activity.event_info;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shadowsparky.news.Requester;
import ru.shadowsparky.news.api.Api;
import ru.shadowsparky.news.callbacks.ResponseHandler;

public class EventModel implements Event.Model {
    String link;

    public EventModel(String link) {
        this.link = link;
    }

    @Override
    public void getEventInfo(ResponseHandler callback) {
        Api api = new Requester().getApi();
        Observable.just(link)
                .observeOn(Schedulers.io())
                .map(item ->
                        api.getEventInfo(item).blockingFirst())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        next ->
                                callback.handle(next),
                        error -> {
                            callback.handle(null);
                            Log.println(Log.DEBUG, "MAIN_TAG", error.toString());
                        }
                );
    }
}
