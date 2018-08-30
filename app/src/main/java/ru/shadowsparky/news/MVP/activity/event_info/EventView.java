package ru.shadowsparky.news.MVP.activity.event_info;

import android.graphics.Color;
import android.os.Bundle;
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

public class EventView extends AppCompatActivity implements Event.View {
    Event.Presenter presenter;
    CategoryResponse item;
    ProgressBar loading;
    RecyclerView list;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        item = (CategoryResponse) getIntent().getSerializableExtra("RESPONSE");
        Toolbar toolbar = findViewById(R.id.event_toolbar);
        list = findViewById(R.id.event_articles_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.event_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        loading = findViewById(R.id.event_loading);
        presenter = new EventPresenter(this, new EventModel(item.getArticle()));
        presenter.onGetEventInfoRequest();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarInit();
    }

    private void toolbarInit() {
        collapsingToolbarLayout.setTitle(getString(R.string.event_info));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent)); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.rgb(255, 255, 255)); //Color of your title
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
    }

    @Override
    public void setAdapter(EventResponse response) {
        List<EventArticle> data = response.getArticle();
        if (data.size() > 0) {
            EventArticle articles = new EventArticle()
                    .setHeader("Предсказание")
                    .setText(response.getPrediction());
            data.add(articles);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            ArticleAdapter adapter = new ArticleAdapter(data);
            list.setLayoutManager(llm);
            list.setHasFixedSize(false);
            list.setAdapter(adapter);
        }
    }

    @Override
    public void setData(EventResponse response) {
        TextView teams = findViewById(R.id.event_teams);
        TextView time = findViewById(R.id.event_time);
        TextView tournament = findViewById(R.id.event_tournament);
        TextView place = findViewById(R.id.event_place);
        teams.setText(response.getTeam1() + " vs " + response.getTeam2());
        time.setText(response.getTime());
        tournament.setText(response.getTournament());
        place.setText(response.getPlace());
    }
}
