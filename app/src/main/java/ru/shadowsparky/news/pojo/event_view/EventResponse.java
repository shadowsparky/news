package ru.shadowsparky.news.pojo.event_view;

import java.io.Serializable;
import java.util.List;

public class EventResponse implements Serializable {
    String team1 = "";
    String team2 = "";
    String time = "";
    String tournament = "";
    String place = "";
    List<EventArticle> article;
    String prediction = "";
}
