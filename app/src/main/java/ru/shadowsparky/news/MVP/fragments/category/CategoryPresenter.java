package ru.shadowsparky.news.MVP.fragments.category;

import ru.shadowsparky.news.callbacks.Response;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public class CategoryPresenter implements Category.Presenter {
    Category.View view;
    Category.Model model;

    public CategoryPresenter(Category.View view, Category.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onGetCategoryRequesting(String category) {
        view.setLoading(true);
        model.getCategoryRequest(this::onRequestHandled, category);
    }

    @Override
    public void onRequestHandled(Response response) {
        if ((response != null) && (response instanceof CategoryEvents)) {
            view.setAdapter((CategoryEvents)response, this::onCardClicked);
        } else {
            view.showErrorToast();
        }
        view.setLoading(false);
    }

    @Override
    public void onCardClicked(Response response) {
        if ((response != null) && (response instanceof CategoryResponse)) {
            view.navigateToEventInfo((CategoryResponse)response);
        }
    }
}
