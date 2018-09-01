package ru.shadowsparky.news.MVP.activity.event_info;

import ru.shadowsparky.news.pojo.event_view.EventResponse;

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
        model.getEventInfo(this::onRequestHandled);
    }

    @Override
    public void onRequestHandled(EventResponse response) {
        if (response != null) {
            view.setAdapter(response);
            view.setData(response);
        } else {
            view.showErrorToast();
        }
        view.setLoading(false);
    }
}
