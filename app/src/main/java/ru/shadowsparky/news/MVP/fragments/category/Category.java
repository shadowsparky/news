package ru.shadowsparky.news.MVP.fragments.category;

import ru.shadowsparky.news.adapter.NewsAdapter;
import ru.shadowsparky.news.callbacks.OnCardClicked;
import ru.shadowsparky.news.callbacks.ResponseHandler;
import ru.shadowsparky.news.callbacks.Response;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public interface Category {
    interface View {
        void setAdapter(CategoryEvents events, OnCardClicked clickListener);
        void setLoading(Boolean result);
        void showErrorToast();
        void setAdapter(NewsAdapter adapter);
        void navigateToEventInfo(CategoryResponse response);
    }
    interface Presenter {
        void onGetCategoryRequesting(String category);
        void onRequestHandled(Response events);
        void onCardClicked(Response response);
    }
    interface Model {
        void getCategoryRequest(ResponseHandler callback, String category);
    }
}
