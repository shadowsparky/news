package ru.shadowsparky.news;

import ru.shadowsparky.news.pojo.category.CategoryResponse;

public interface OnCardClickedCallback {
    void cardClicked(CategoryResponse response);
}
