package ru.shadowsparky.news.MVP.activity.event_info;

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
                view.setAdapter(response);
                view.setData(response);
            } else {
                view.showErrorToast();
            }
            view.setLoading(false);
        };
        model.getEventInfo(callback);
    }
}
