package ru.shadowsparky.news.MVP.activity.event_info;

public interface Event {
    interface View {
        void setLoading(Boolean result);
        void showToast(int id);
    }
    interface Presenter {
        void onGetEventInfoRequest();
    }
    interface Model {
        void getEventInfo(EventModel.GetEventInfoCallback callback);
    }
}
