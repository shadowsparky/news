package ru.shadowsparky.news.MVP.fragments.category;

import ru.shadowsparky.news.R;
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
        CategoryModel.RequestCallback callback = (result) -> {
            if (result != null) {
                view.setAdapter(result, this::onCardClicked);
            } else {
                view.showToast(R.string.connection_error);
            }
            view.setLoading(false);
        };
        model.getCategoryRequest(callback, category);
    }

    public void onCardClicked(CategoryResponse response) {
        view.navigateToEventInfo(response);
    }
}
