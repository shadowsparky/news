package ru.shadowsparky.news.fragments.category;

import ru.shadowsparky.news.adapter.NewsList;

public interface Category {
    interface View {
        void setAdapter(NewsList adapter);
        void setLoading(Boolean result);
        void showToast(int id);
    }
    interface Presenter {
        void onGetCategoryRequesting(String category);
    }
    interface Model {
        void getCategoryRequest(CategoryModel.RequestCallback callback, String category);
    }
}
