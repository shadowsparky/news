package ru.shadowsparky.news.MVP.activity.event_info;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import ru.shadowsparky.news.pojo.event_view.EventResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventPresenterTest {
    Event.Presenter presenter;
    Event.Model model;
    Event.View view;


    @Before
    public void setUp() throws Exception {
        model = mock(EventModel.class);
        view = mock(Event.View.class);
        presenter = new EventPresenter(view, model);
    }

    @Test
    public void onGetEventInfoRequest() {
        presenter.onGetEventInfoRequest();
        verify(model).getEventInfo(any(Event.GetEventInfoCallback.class));
    }

    @Test
    public void onFailureRequest() {
        presenter.onRequestHandled(null);
        verify(view).showErrorToast();
    }

    @Test
    public void onSuccessRequest() {
        EventResponse response = new EventResponse();
        presenter.onRequestHandled(response);
        verify(view).setAdapter(response);
        verify(view).setData(response);
    }
}