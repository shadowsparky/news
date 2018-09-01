package ru.shadowsparky.news.MVP.fragments.category;

import ru.shadowsparky.news.OnCardClickedCallback;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public interface Category {
    interface View {
        void setAdapter(CategoryEvents events, OnCardClickedCallback clickListener);
        void setLoading(Boolean result);
        void showErrorToast();
        void navigateToEventInfo(CategoryResponse response);
    }
    interface Presenter {
        void onGetCategoryRequesting(String category);
        void onRequestHandled(CategoryEvents events);
        void onCardClicked(CategoryResponse response);
    }
    interface Model {
        void getCategoryRequest(RequestCallback callback, String category);
    }

    interface RequestCallback {
        void handleRequest(CategoryEvents events);
    }
}
