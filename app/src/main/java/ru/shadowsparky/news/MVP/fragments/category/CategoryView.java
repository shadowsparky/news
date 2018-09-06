package ru.shadowsparky.news.MVP.fragments.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ru.shadowsparky.news.MVP.activity.event_info.EventView;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.adapter.NewsAdapter;
import ru.shadowsparky.news.callbacks.OnCardClicked;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;

import static ru.shadowsparky.news.ListActivity.CATEGORY;
import static ru.shadowsparky.news.callbacks.Response.RESPONSE;


public class CategoryView extends Fragment implements Category.View {
    public static final String ADAPTER = "ADAPTER";
    public static final String EVENTS = "EVENTS";
    public static final String PRESENTER = "PRESENTER";
    RecyclerView list;
    Category.Presenter presenter;
    SwipeRefreshLayout refresher;
    String category;
    NewsAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CategoryPresenter(this, new CategoryModel());
        Bundle arguments = getArguments();
        category = arguments.getString(CATEGORY);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onGetCategoryRequesting(category);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.category_list);
        refresher = view.findViewById(R.id.refresher);
        refresher.setOnRefreshListener(() -> presenter.onGetCategoryRequesting(category));
    }

    @Override
    public void setAdapter(CategoryEvents events, OnCardClicked callback) {
        adapter = new NewsAdapter(events, callback);
        setAdapter(adapter);
    }

    @Override
    public void setAdapter(NewsAdapter adapter) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        list.setLayoutManager(llm);
        list.setHasFixedSize(false);
        list.setAdapter(adapter);
    }

    @Override
    public void setLoading(Boolean result) {
        if (result) {
            list.setVisibility(View.GONE);
        } else {
            list.setVisibility(View.VISIBLE);
        }
        refresher.setRefreshing(result);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToEventInfo(CategoryResponse response) {
        Intent i = new Intent(getContext(), EventView.class);
        i.putExtra(RESPONSE, response);
        i.putExtra(CATEGORY, category);
        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}