package ru.shadowsparky.news.pojo.event_view;

import java.io.Serializable;
import java.util.List;

import ru.shadowsparky.news.callbacks.Response;

public class EventResponse implements Response {
    String team1 = "";
    String team2 = "";
    String time = "";
    String tournament = "";
    String place = "";
    List<EventArticle> article;
    String prediction = "";

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTime() {
        return time;
    }

    public String getTournament() {
        return tournament;
    }

    public String getPlace() {
        return place;
    }

    public List<EventArticle> getArticle() {
        return article;
    }

    public String getPrediction() {
        return prediction;
    }
}
