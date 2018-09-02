package ru.shadowsparky.news.callbacks;

import java.io.Serializable;

public interface ResponseHandler extends Serializable {
    void handle(Response response);
}
