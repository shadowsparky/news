package ru.shadowsparky.news.MVP.fragments.category;

import org.junit.Before;
import org.junit.Test;

import ru.shadowsparky.news.callbacks.OnCardClicked;
import ru.shadowsparky.news.callbacks.ResponseHandler;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CategoryPresenterTest {
    Category.View view;
    Category.Model model;
    Category.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(Category.View.class);
        model = mock(Category.Model.class);
        presenter = new CategoryPresenter(view, model);
    }

    @Test
    public void onGetCategoryRequesting() {
        presenter.onGetCategoryRequesting("category");
        verify(model).getCategoryRequest(any(ResponseHandler.class), eq("category"));
    }

    @Test
    public void onFailureRequest() {
        presenter.onRequestHandled(null);
        verify(view).showErrorToast();
    }

    @Test
    public void onSuccessRequest() {
        CategoryEvents events = new CategoryEvents();
        presenter.onRequestHandled(events);
        verify(view).setAdapter(eq(events), any(OnCardClicked.class));
    }

    @Test
    public void onSuccessCardClicked() {
        CategoryResponse response = new CategoryResponse();
        presenter.onCardClicked(response);
        verify(view).navigateToEventInfo(response);
    }
}