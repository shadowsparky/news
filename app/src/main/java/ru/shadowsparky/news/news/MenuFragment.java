package ru.shadowsparky.news.news;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.shadowsparky.news.news.adapter.NewsList;
import ru.shadowsparky.news.news.pojo.category.CategoryEvents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuFragment extends Fragment {
    NewsList adapter;
    RecyclerView list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        CategoryEvents events = (CategoryEvents) arguments.getSerializable("EVENTS");
        String category = arguments.getString("CATEGORY");
        adapter = new NewsList(events, category);
        list = view.findViewById(R.id.category_list);
        setAdapter(adapter);
    }

    public void setAdapter(NewsList adapter) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        list.setLayoutManager(llm);
        list.setHasFixedSize(false);
        list.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
