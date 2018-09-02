package ru.shadowsparky.news.MVP.activity.event_info;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.adapter.ArticleAdapter;
import ru.shadowsparky.news.pojo.category.CategoryResponse;
import ru.shadowsparky.news.pojo.event_view.EventArticle;
import ru.shadowsparky.news.pojo.event_view.EventResponse;

import static ru.shadowsparky.news.ListActivity.BASKETBALL_ITEM;
import static ru.shadowsparky.news.ListActivity.CATEGORY;
import static ru.shadowsparky.news.ListActivity.CYBERSPORT_ITEM;
import static ru.shadowsparky.news.ListActivity.FOOTBALL_ITEM;
import static ru.shadowsparky.news.ListActivity.HOCKEY_ITEM;
import static ru.shadowsparky.news.ListActivity.TENNIS_ITEM;
import static ru.shadowsparky.news.ListActivity.VOLLEYBALL_ITEM;
import static ru.shadowsparky.news.MVP.fragments.category.CategoryView.ADAPTER;
import static ru.shadowsparky.news.callbacks.Response.RESPONSE;

public class EventView extends AppCompatActivity implements Event.View {
    public static final String PLACE = "PLACE";
    public static final String TOURNAMENT = "TOURNAMENT";
    public static final String TIME = "TIME";
    public static final String TEAMS = "TEAMS";
    Event.Presenter presenter;
    CategoryResponse item;
    ProgressBar loading;
    RecyclerView list;
    String category;
    TextView teams;
    TextView time;
    TextView tournament;
    TextView place;
    ArticleAdapter adapter;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        item = (CategoryResponse) getIntent().getSerializableExtra(RESPONSE);
        category = getIntent().getStringExtra(CATEGORY);
        list = findViewById(R.id.event_articles_list);
        teams = findViewById(R.id.event_teams);
        loading = findViewById(R.id.event_loading);
        presenter = new EventPresenter(this, new EventModel(item.getArticle()));
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        Toolbar toolbar = findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        toolbarInit();
        time = findViewById(R.id.event_time);
        tournament = findViewById(R.id.event_tournament);
        place = findViewById(R.id.event_place);
        if (savedInstanceState == null) {
            presenter.onGetEventInfoRequest();
        }
        Log.println(Log.DEBUG, "MAIN_TAG", "link: " + item.getArticle());
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setTitle(R.string.event_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter = (ArticleAdapter) savedInstanceState.getSerializable(ADAPTER);
        if (adapter != null) {
            setAdapter(adapter);
            teams.setText(savedInstanceState.getString(TEAMS));
            time.setText(savedInstanceState.getString(TIME));
            tournament.setText(savedInstanceState.getString(TOURNAMENT));
            place.setText(savedInstanceState.getString(PLACE));
        } else {
            presenter.onGetEventInfoRequest();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ADAPTER, adapter);
        outState.putString(TEAMS, teams.getText().toString());
        outState.putString(TIME, time.getText().toString());
        outState.putString(TOURNAMENT, tournament.getText().toString());
        outState.putString(PLACE, place.getText().toString());
    }

    private void toolbarInit() {
        collapsingToolbarLayout.setTitle(getString(R.string.event_info));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.rgb(255, 255, 255));
        switch (category) {
            case FOOTBALL_ITEM: {
                collapsingToolbarLayout.setBackgroundResource(R.drawable.football_cover);
                break;
            }
            case HOCKEY_ITEM: {
                collapsingToolbarLayout.setBackgroundResource(R.drawable.hockey_cover);
                break;
            }
            case TENNIS_ITEM: {
                collapsingToolbarLayout.setBackgroundResource(R.drawable.tennis_cover);
                break;
            }
            case BASKETBALL_ITEM: {
                collapsingToolbarLayout.setBackgroundResource(R.drawable.basketball_cover);
                break;
            }
            case VOLLEYBALL_ITEM: {
                collapsingToolbarLayout.setBackgroundResource(R.drawable.volleyball_cover);
                break;
            }
            case CYBERSPORT_ITEM: {
                collapsingToolbarLayout.setBackgroundResource(R.drawable.cybersport_cover);
                break;
            }
        }
    }

    @Override
    public void setLoading(Boolean result) {
        if (result) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
        collapsingToolbarLayout.setTitle(getString(R.string.event_loading_error));
        teams.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        teams.setText(R.string.event_loading_error);
        collapsingToolbarLayout.setBackgroundResource(android.R.color.holo_red_light);
    }

    @Override
    public void setAdapter(EventResponse response) {
        List<EventArticle> data = response.getArticle();
        if (data.size() > 0) {
            EventArticle articles = new EventArticle()
                    .setHeader("Предсказание")
                    .setText(response.getPrediction());
            data.add(articles);
            adapter = new ArticleAdapter(data);
            setAdapter(adapter);
        }
    }

    @Override
    public void setAdapter(ArticleAdapter adapter) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        list.setLayoutManager(llm);
        list.setHasFixedSize(false);
        list.setAdapter(adapter);
    }

    @Override
    public void setData(EventResponse response) {
        teams.setText(response.getTeam1() + " vs " + response.getTeam2());
        time.setText(response.getTime());
        tournament.setText(response.getTournament());
        place.setText(response.getPlace());
    }
}
    