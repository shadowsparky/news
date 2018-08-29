package ru.shadowsparky.news.MVP.activity.event_info;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public class EventView extends AppCompatActivity implements Event.View {
    Event.Presenter presenter;
    CategoryResponse item;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        item = (CategoryResponse) getIntent().getSerializableExtra("RESPONSE");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.event_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        loading = findViewById(R.id.event_loading);
        presenter = new EventPresenter(this, new EventModel(item.getArticle()));
        presenter.onGetEventInfoRequest();
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
    public void showToast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}
