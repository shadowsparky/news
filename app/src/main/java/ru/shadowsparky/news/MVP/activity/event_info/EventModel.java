package ru.shadowsparky.news.MVP.activity.event_info;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.shadowsparky.news.Requester;
import ru.shadowsparky.news.api.Api;
import ru.shadowsparky.news.pojo.event_view.EventResponse;

public class EventModel implements Event.Model {
    String link;

    interface GetEventInfoCallback {
        void handleRequest(EventResponse response);
    }

    public EventModel(String link) {
        this.link = link;
    }

    @Override
    public void getEventInfo(GetEventInfoCallback callback) {
        Api api = new Requester().getApi();
        Observable.just(link)
                .observeOn(Schedulers.io())
                .map(item -> api.getEventInfo(item).blockingFirst())
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
