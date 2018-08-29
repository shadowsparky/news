package ru.shadowsparky.news.MVP.activity.event_info;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

public class EventView extends AppCompatActivity implements Event.View {
    Event.Presenter presenter;
    CategoryResponse item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        item = (CategoryResponse) getIntent().getSerializableExtra("RESPONSE");
        presenter = new EventPresenter(this, new EventModel(item.getArticle()));
        presenter.onGetEventInfoRequest();
    }

    @Override
    public void setLoading(Boolean result) {

    }

    @Override
    public void showToast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}
