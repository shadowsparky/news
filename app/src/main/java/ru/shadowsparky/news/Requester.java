package ru.shadowsparky.news;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.shadowsparky.news.api.Api;

public class Requester {
    private Requester instance;

    public Requester getInstance() {
        if (instance == null) {
            instance = new Requester();
        }
        return instance;
    }

    public Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mikonatoruri.win")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }
}
