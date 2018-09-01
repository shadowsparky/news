package ru.shadowsparky.news.MVP.activity.event_info;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventModelTest {

    Event.Model model;
    Event.GetEventInfoCallback callback;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
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
        model = new EventModel("example_link");
        callback = mock(Event.GetEventInfoCallback.class);
    }

    @Test
    public void getEventInfo() {
        model.getEventInfo(callback);
        verify(callback).handleRequest(any());
    }
}