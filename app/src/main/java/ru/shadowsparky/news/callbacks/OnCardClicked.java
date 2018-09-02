package ru.shadowsparky.news.callbacks;

import java.io.Serializable;

public interface OnCardClicked extends Serializable {
    void cardClicked(Response response);
}
