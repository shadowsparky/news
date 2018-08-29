package ru.shadowsparky.news.MVP.activity.event_info;

import android.util.Log;

import ru.shadowsparky.news.R;

public class EventPresenter implements Event.Presenter {
    Event.View view;
    Event.Model model;

    public EventPresenter(Event.View view, Event.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onGetEventInfoRequest() {
        view.setLoading(true);
        EventModel.GetEventInfoCallback callback = response -> {
            if (response != null) {
                Log.println(Log.DEBUG, "MAIN_TAG", response.toString());
            } else {
                view.showToast(R.string.connection_error);
            }
            view.setLoading(false);
        };
        model.getEventInfo(callback);
    }
}
