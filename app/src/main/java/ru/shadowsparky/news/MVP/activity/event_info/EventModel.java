package ru.shadowsparky.news.MVP.activity.event_info;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shadowsparky.news.Requester;
import ru.shadowsparky.news.api.Api;

public class EventModel implements Event.Model {
    String link;

    public EventModel(String link) {
        this.link = link;
    }

    @Override
    public void getEventInfo(Event.GetEventInfoCallback callback) {
        Api api = new Requester().getApi();
        Observable.just(link)
                .observeOn(Schedulers.io())
                .map(item -> api.getEventInfo(item).blockingFirst())
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
