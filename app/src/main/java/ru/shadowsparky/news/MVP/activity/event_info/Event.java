package ru.shadowsparky.news.MVP.activity.event_info;

import ru.shadowsparky.news.callbacks.ResponseHandler;
import ru.shadowsparky.news.callbacks.Response;
import ru.shadowsparky.news.pojo.event_view.EventResponse;

public interface Event {
    interface View {
        void setLoading(Boolean result);
        void showErrorToast();
        void setAdapter(EventResponse response);
        void setData(EventResponse response);
    }
    interface Presenter {
        void onGetEventInfoRequest();
        void onRequestHandled(Response response);
    }
    interface Model {
        void getEventInfo(ResponseHandler callback);
    }
}
