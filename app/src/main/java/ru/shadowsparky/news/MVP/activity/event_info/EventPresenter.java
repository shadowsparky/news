package ru.shadowsparky.news.MVP.activity.event_info;

import ru.shadowsparky.news.callbacks.Response;
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
    public void onRequestHandled(Response response) {
        if ((response != null) && (response instanceof EventResponse)) {
            EventResponse eventResponse = (EventResponse) response;
            view.setAdapter(eventResponse);
            view.setData(eventResponse);
        } else {
            view.showErrorToast();
        }
        view.setLoading(false);
    }
}
