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
import ru.shadowsparky.news.OnCardClickedCallback;
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.adapter.NewsAdapter;
import ru.shadowsparky.news.pojo.category.CategoryEvents;
import ru.shadowsparky.news.pojo.category.CategoryResponse;


public class CategoryView extends Fragment implements Category.View {
    RecyclerView list;
    Category.Presenter presenter;
    SwipeRefreshLayout refresher;
    String category;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CategoryPresenter(this, new CategoryModel());
        Bundle arguments = getArguments();
        category = arguments.getString("CATEGORY");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.category_list);
        refresher = view.findViewById(R.id.refresher);
        refresher.setOnRefreshListener(()-> presenter.onGetCategoryRequesting(category));
        presenter.onGetCategoryRequesting(category);
    }

    @Override
    public void setAdapter(CategoryEvents events, OnCardClickedCallback callback) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        NewsAdapter adapter = new NewsAdapter(events, callback);
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
        i.putExtra("RESPONSE", response);
        i.putExtra("CATEGORY", category);
        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
