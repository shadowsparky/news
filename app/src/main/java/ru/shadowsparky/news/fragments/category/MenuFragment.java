package ru.shadowsparky.news.fragments.category;


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
import ru.shadowsparky.news.R;
import ru.shadowsparky.news.adapter.NewsList;


public class MenuFragment extends Fragment implements Category.View {
    NewsList adapter;
    RecyclerView list;
    Category.Presenter presenter;
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
        presenter.onGetCategoryRequesting(category);
    }

    @Override
    public void setAdapter(NewsList adapter) {
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
    }

    @Override
    public void showToast(int id) {
        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
