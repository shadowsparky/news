package ru.shadowsparky.news.MVP.fragments.category;

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
import ru.shadowsparky.news.pojo.category.CategoryEvents;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CategoryModelTest {
    Category.Model model;
    Category.RequestCallback nullCallback;
    Category.RequestCallback nonNullCallback;

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
        model = spy(new CategoryModel());
        nullCallback = spy(new Category.RequestCallback() {
            @Override
            public void handleRequest(CategoryEvents events) {
                assertNull(events);
            }
        });
        nonNullCallback = spy(new Category.RequestCallback() {
            @Override
            public void handleRequest(CategoryEvents events) {
                assertNotNull(events);
            }
        });
    }

    @Test
    public void onCategoryFailure() {
        model.getCategoryRequest(nullCallback, "fail");
        verify(nullCallback).handleRequest(null);
    }

    @Test
    public void onCategorySuccess() {
        model.getCategoryRequest(nonNullCallback, "football");
        verify(nonNullCallback).handleRequest(any(CategoryEvents.class));
    }
}