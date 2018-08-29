package ru.shadowsparky.news.MVP.fragments.category;

import ru.shadowsparky.news.OnCardClickedCallback;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public interface Category {
    interface View {
        void setAdapter(CategoryEvents events, OnCardClickedCallback clickListener);
        void setLoading(Boolean result);
        void showToast(int id);
        void navigateToEventInfo(CategoryResponse response);
    }
    interface Presenter {
        void onGetCategoryRequesting(String category);
    }
    interface Model {
        void getCategoryRequest(CategoryModel.RequestCallback callback, String category);
    }
}
