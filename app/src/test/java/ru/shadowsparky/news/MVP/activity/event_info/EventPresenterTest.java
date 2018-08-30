package ru.shadowsparky.news.MVP.activity.event_info;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import ru.shadowsparky.news.pojo.event_view.EventResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventPresenterTest {
    Event.Presenter presenter;
    Event.Model model;
    Event.View view;
    Event.GetEventInfoCallback callback;



    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Scheduler.Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp() throws Exception {
        model = Mockito.spy(new EventModel("/2018/02/15/lester-sheffild-junajted-prognoz-na-kubok-anglii-16-02-2018"));
        view = mock(Event.View.class);
        callback = mock(Event.GetEventInfoCallback.class);
        presenter = new EventPresenter(view, model);
    }

    @Test
    public void onGetEventSuccessInfoRequest() {
        presenter.onGetEventInfoRequest();
        verify(model).getEventInfo(any(Event.GetEventInfoCallback.class));
        verify(view).setData(any(EventResponse.class));
        verify(view).setAdapter(any(EventResponse.class));
    }

    @Test
    public void onGetEventFailInfoRequest() {
        model = Mockito.spy(new EventModel(null));
        presenter.onGetEventInfoRequest();
        verify(view).showErrorToast();
    }
}